package com.example.weatherapp.network

import com.example.weatherapp.models.CurrentWeatherRes
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double, @Query("skip") lon: Double, @Query("appid") appId: String
    ): CurrentWeatherRes

}