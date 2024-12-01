package com.example.weatherapp.nav

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.ui.screens.HomeScreen
import com.example.weatherapp.ui.screens.SearchScreen
import com.example.weatherapp.utils.LocalNavController

@Composable
fun WeatherAppNavGraph(
    navController: NavHostController, startDestination: String = NavScreens.HOME_SCREEN
) {
    val currentNav = LocalNavController.current

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = NavScreens.HOME_SCREEN) {
            HomeScreen()
            BackHandler {
                (currentNav.context as ComponentActivity).finish()
            }
        }
        composable(route = NavScreens.SEARCH_SCREEN) {
            SearchScreen()
        }
    }
}