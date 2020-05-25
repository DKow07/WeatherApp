package com.example.weatherapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.Daily
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.service.impl.WeatherManager
import com.example.weatherapp.utils.WeatherAppUtils
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment(), HomeContract.View {

    private lateinit var presenter: HomePresenter

    private lateinit var adapter: ForecastAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var cityNameTextView: TextView
    private lateinit var dayNameTextView: TextView
    private lateinit var weatherIconImageView: ImageView
    private lateinit var weatherTypeTextView: TextView
    private lateinit var weatherDescTextView: TextView
    private lateinit var mainTempTextView: TextView
    private lateinit var backgroud: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        setupUI(view)
        val weatherManager = WeatherManager()
        presenter = HomePresenter(weatherManager, this)
        presenter.getWeatherData()
        return view
    }

    override fun onSuccessGetWeatherData(weatherData: WeatherData) {
        setCurrentWeather(weatherData.daily[0])

        var forecastData = mutableListOf<Daily>()
        forecastData.add(weatherData.daily[1])
        forecastData.add(weatherData.daily[2])
        forecastData.add(weatherData.daily[3])

        adapter.updateData(forecastData)
    }

    private fun setCurrentWeather(currentWeather: Daily) {
        dayNameTextView.text = WeatherAppUtils.getDayStringFromTimestamp(currentWeather.dateTime)
        weatherTypeTextView.text = currentWeather.weather[0].main
        mainTempTextView.text = (currentWeather.temp.day.toInt()).toString() + "\u2103"
        weatherDescTextView.text = "(" + currentWeather.weather[0].desc + ")"
        setWeatherIconAndBackground(WeatherAppUtils.weatherTypeToInt[currentWeather.weather[0].main] ?: 0)

    }

    override fun onErrorGetWeatherData() {
        var a = 5
    }

    private fun setupUI(view: View) {
        cityNameTextView = view.findViewById(R.id.cityNameText)
        dayNameTextView = view.findViewById(R.id.dayNameText)
        weatherIconImageView = view.findViewById(R.id.weatherIconImage)
        weatherTypeTextView = view.findViewById(R.id.weatherTypeText)
        weatherDescTextView = view.findViewById(R.id.weatherDescText)
        mainTempTextView = view.findViewById(R.id.mainTempText)
        backgroud = view.findViewById(R.id.layoutBackground)
        recyclerView = view.findViewById(R.id.forecastWeatherRecyclerView)
        linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager
        adapter = ForecastAdapter()
        recyclerView.adapter = adapter
    }

    private fun setWeatherIconAndBackground(weatherId: Int) {
        when(weatherId) {
            WeatherAppUtils.weatherTypeToInt["Rain"], WeatherAppUtils.weatherTypeToInt["Snow"]
                , WeatherAppUtils.weatherTypeToInt["Thunderstorm"], WeatherAppUtils.weatherTypeToInt["Drizzle"] -> {
                backgroud.setBackgroundResource(R.drawable.rainy_background)
                weatherIconImageView.setImageResource(R.drawable.rainy_white)
                activity!!.window.setStatusBarColor(activity!!.resources.getColor(R.color.rainyColorLight))
            }
            WeatherAppUtils.weatherTypeToInt["Clear"] -> {
                backgroud.setBackgroundResource(R.drawable.sunny_background)
                weatherIconImageView.setImageResource(R.drawable.sunny_white)
                activity!!.window.setStatusBarColor(activity!!.resources.getColor(R.color.sunnyColorLight))
            }
            WeatherAppUtils.weatherTypeToInt["Clouds"] -> {
                backgroud.setBackgroundResource(R.drawable.cloudy_background)
                weatherIconImageView.setImageResource(R.drawable.cloudy_white)
                activity!!.window.setStatusBarColor(activity!!.resources.getColor(R.color.cloudyColorLight))
            }
            else -> {
                weatherTypeTextView.text = "Ups something went wrong :(";
            }
        }
    }
}