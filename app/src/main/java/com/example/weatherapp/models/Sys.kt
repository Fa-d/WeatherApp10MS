package com.example.weatherapp.models


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("sunrise")
    val sunrise: Int = 0,
    @SerializedName("sunset")
    val sunset: Int = 0
)