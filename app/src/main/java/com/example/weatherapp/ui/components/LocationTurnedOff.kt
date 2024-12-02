package com.example.weatherapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weatherapp.utils.LocalMainViewModel


@Composable
fun LocationTurnedOff(onGranted: @Composable BoxScope.() -> Unit) {
    val mainViewModel = LocalMainViewModel.current
    val isLocationOn = mainViewModel.isLocationServiceOn.collectAsState()
    Scaffold { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            if (!isLocationOn.value) {
                Text("Location turned OFF \nPlease turn ON to continue. ")
            } else {
                onGranted()
            }
        }
    }
}