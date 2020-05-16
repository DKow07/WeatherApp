package com.example.weatherapp.service.impl

import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.service.IWeatherManager
import com.example.weatherapp.service.RequestCallback
import com.example.weatherapp.service.api.IWeatherAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NullPointerException

class WeatherManager : IWeatherManager {

    private lateinit var retrofitClient: RetrofitClient
    private lateinit var api: IWeatherAPI

    init {
        retrofitClient = RetrofitClient()
        api = retrofitClient.getRetrofit().create(IWeatherAPI::class.java)
    }

    override fun getWeatherData(lat:Float, lon:Float, callback: RequestCallback<WeatherData>) {
        api.getWeatherData(lat, lon).enqueue(object: Callback<WeatherData> {
            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                if(response.body() != null) {
                    callback.onSuccess(response.body()!!)
                } else {
                    callback.onError(Throwable()) //TODO: change this later
                }

            }

        })
    }
}