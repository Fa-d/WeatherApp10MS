package com.example.weatherapp.core

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.network.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getCurrentWeather(lat: Double, lon: Double) =
        apiService.getCurrentWeather(lat, lon, appId = BuildConfig.apiKey)
}