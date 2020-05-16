package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class WeatherData (
    @SerializedName("daily")
    var daily: List<Daily>
)