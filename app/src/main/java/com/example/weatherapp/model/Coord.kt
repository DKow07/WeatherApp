package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Coord (
    @SerializedName("lon")
    var lon: Float,
    @SerializedName("lat")
    var lat: Float
)