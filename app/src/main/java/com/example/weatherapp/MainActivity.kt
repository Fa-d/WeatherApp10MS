package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.nav.WeatherAppNavGraph
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.utils.LocalMainViewModel
import com.example.weatherapp.utils.LocalNavController
import com.example.weatherapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
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
                    WeatherAppNavGraph(navController)

                }
            }
        }
    }
}
