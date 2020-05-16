package com.example.weatherapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.service.impl.WeatherManager

class HomeFragment : Fragment(), HomeContract.View {

    private lateinit var presenter: HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        val weatherManager = WeatherManager()
        presenter = HomePresenter(weatherManager, this)

        presenter.getWeatherData()

        return view
    }

    override fun onSuccessGetWeatherData(weatherData: WeatherData) {
        var a = weatherData
    }

    override fun onErrorGetWeatherData() {
        var a = 5
    }
}