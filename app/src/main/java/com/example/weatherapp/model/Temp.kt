package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Temp (
    @SerializedName("day")
    var day: Float
)