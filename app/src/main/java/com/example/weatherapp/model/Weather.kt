package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Weather (
    @SerializedName("main")
    var main: String,
    @SerializedName("description")
    var desc: String,
    @SerializedName("icon")
    var icon: String
)