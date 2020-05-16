package com.example.weatherapp.service.api

import com.example.weatherapp.model.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherAPI {

    @GET("onecall?exclude=current,minutely,hourly&appid=4b848eb0adc2278ec2165cff24669397&units=metric")
    fun getWeatherData(@Query("lat") lat:Float, @Query("lon") lon:Float): Call<WeatherData>

}