package com.example.weatherapp

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.nav.WeatherAppNavGraph
import com.example.weatherapp.ui.components.LocationTurnedOff
import com.example.weatherapp.ui.components.PermissionBox
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.utils.LocalMainViewModel
import com.example.weatherapp.utils.LocalNavController
import com.example.weatherapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()
            CompositionLocalProvider(
                LocalNavController provides navController, LocalMainViewModel provides viewModel
            ) {
                WeatherAppTheme {
                    PermissionBox(
                        permissions = listOf(
                            android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                        ),
                        requiredPermissions = listOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                        contentAlignment = Alignment.Center,
                    ) {
                        LocationTurnedOff {
                            WeatherAppNavGraph(navController)
                        }
                    }

                }
            }
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        filter.addAction(Intent.ACTION_PROVIDER_CHANGED)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(locationStatusReceiver, filter, RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(locationStatusReceiver, filter)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(locationStatusReceiver)
    }

    private val locationStatusReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == LocationManager.PROVIDERS_CHANGED_ACTION) {
                val locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
                val isLocationEnabled = isLocationEnabled(locationManager)
                Log.e("isLocationEnabled", isLocationEnabled.toString())
                lifecycleScope.launch {
                    viewModel.isLocationServiceOn.emit(isLocationEnabled)
                }
            }
        }
    }
}
