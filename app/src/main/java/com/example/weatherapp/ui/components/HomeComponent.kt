package com.example.weatherapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.utils.LocalMainViewModel

@Composable
fun HomeComponent() {
    val viewModel = LocalMainViewModel.current
    val currWeather = viewModel.currentWeather.collectAsState()
    Card(shape = RoundedCornerShape(30.dp), elevation = CardDefaults.cardElevation(10.dp)) {
        Box(modifier = Modifier.padding(20.dp)) {
            if (currWeather.value.weather.isNotEmpty()) {
                Column {
                    Text(currWeather.value.name)
                    Spacer(Modifier.height(20.dp))
                    Text("${currWeather.value.main.temp} .C")
                    Spacer(Modifier.height(10.dp))
                    Text(currWeather.value.weather[0].main)
                    Spacer(Modifier.height(10.dp))
                    Text(currWeather.value.wind.speed.toString())
                }
            } else {
                CircularProgressIndicator()
            }
        }
    }
}



