package com.example.weatherapp.ui.home

import com.example.weatherapp.model.WeatherData

interface HomeContract {

    interface Presenter {
        fun getWeatherData()
    }

    interface View {
        fun onSuccessGetWeatherData(weatherData: WeatherData)

        fun onErrorGetWeatherData()
    }
}