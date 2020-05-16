package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class MainData (
    @SerializedName("temp")
    var temp: Float,
    @SerializedName("feels_like")
    var feelLike: Float,
    @SerializedName("pressure")
    var pressure: Int,
    @SerializedName("humidity")
    var humidity: Int
)