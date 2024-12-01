package com.example.weatherapp.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.weatherapp.utils.LocalMainViewModel

@Composable
fun HomeScreen() {
    val viewModel = LocalMainViewModel.current
    LaunchedEffect(null) {
        viewModel.getCurrentWeather()
    }
    Text("HomeScreen")
}