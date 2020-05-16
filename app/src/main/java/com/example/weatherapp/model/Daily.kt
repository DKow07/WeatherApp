package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Daily (
    @SerializedName("dt")
    var dateTime: Long,
    @SerializedName("temp")
    var temp: Temp,
    @SerializedName("feels_like")
    var feelsLike: Temp,
    @SerializedName("pressure")
    var pressure: Float,
    @SerializedName("humidity")
    var humidity: Float,
    @SerializedName("weather")
    var weather: List<Weather>
)