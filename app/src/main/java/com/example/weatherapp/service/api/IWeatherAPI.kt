package com.example.weatherapp.service.api

import com.example.weatherapp.model.WeatherData
import retrofit2.Call
import retrofit2.http.GET

interface IWeatherAPI {

    @GET("weather?q=Lodz&appid=4b848eb0adc2278ec2165cff24669397&units=metric")
    fun getWeatherData(): Call<WeatherData>

}