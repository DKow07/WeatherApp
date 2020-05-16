package com.example.weatherapp.service

import com.example.weatherapp.model.WeatherData

interface IWeatherManager {
    fun getWeatherData(lat:Float, lon:Float, callback: RequestCallback<WeatherData>)
}