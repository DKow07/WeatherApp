package com.example.weatherapp.ui.home

import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.service.RequestCallback
import com.example.weatherapp.service.impl.WeatherManager

class HomePresenter(private val weatherManager: WeatherManager, val view: HomeContract.View) : HomeContract.Presenter{

    private val lodzLoc = Pair(51.75f, 19.47f)

    override fun getWeatherData() {
        weatherManager.getWeatherData(lodzLoc.first, lodzLoc.second, object: RequestCallback<WeatherData> {
            override fun onSuccess(data: WeatherData) {
                view.onSuccessGetWeatherData(data)
            }

            override fun onError(throwable: Throwable) {
                view.onErrorGetWeatherData()
            }

            override fun onCancel() {
                TODO("Not yet implemented")
            }

        })
    }
}