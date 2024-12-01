package com.example.weatherapp.ui.screens

import android.Manifest
import android.content.Context
import android.location.LocationManager
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.example.weatherapp.utils.LocalMainViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@Composable
fun HomeScreen() {
    val viewModel = LocalMainViewModel.current
    val context = LocalContext.current
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    val scope = rememberCoroutineScope()
    val cancellationTokenSource = remember { CancellationTokenSource() }


    LaunchedEffect(null) {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(context, "Please enable GPS", Toast.LENGTH_SHORT).show()
        }
        scope.launch(Dispatchers.IO) {
            locationClient.getCurrentLocation(
                Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                cancellationTokenSource.token,
            ).addOnSuccessListener { result2 ->
                if (result2 != null) {
                    viewModel.getCurrentWeather(result2.latitude, result2.longitude)
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            cancellationTokenSource.cancel()
        }
    }

    Text("HomeScreen")
}