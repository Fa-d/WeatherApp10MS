package com.example.weatherapp.utils

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import com.example.weatherapp.viewmodels.MainViewModel

val LocalNavController = staticCompositionLocalOf<NavController> {
    error("NavController not provided")
}
val LocalMainViewModel = staticCompositionLocalOf<MainViewModel> {
    error("MainViewModel not provided")
}
