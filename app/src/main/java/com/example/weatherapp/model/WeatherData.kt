package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class WeatherData (
    @SerializedName("coord")
    var coord: Coord,
    @SerializedName("weather")
    var weather: List<Weather>,
    @SerializedName("main")
    var mainData: MainData
)