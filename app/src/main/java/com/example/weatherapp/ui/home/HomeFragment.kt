package com.example.weatherapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.model.Daily
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.service.impl.WeatherManager
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment(), HomeContract.View {

    private lateinit var presenter: HomePresenter

    private lateinit var cityNameTextView: TextView
    private lateinit var dayNameTextView: TextView
    private lateinit var weatherIconImageView: ImageView
    private lateinit var weatherTypeTextView: TextView
    private lateinit var weatherDescTextView: TextView
    private lateinit var mainTempTextView: TextView
    private lateinit var backgroud: ConstraintLayout

    private val weatherTypeToInt = mapOf("Rain" to 1, "Clear" to 2, "Clouds" to 3, "Snow" to 4, "Thunderstorm" to 5, "Drizzle" to 6)

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
    }

    private fun setCurrentWeather(currentWeather: Daily) {
        dayNameTextView.text = getDayStringFromTimestamp(currentWeather.dateTime)
        weatherTypeTextView.text = currentWeather.weather[0].main
        mainTempTextView.text = (currentWeather.temp.day.toInt()).toString() + "\u2103"
        weatherDescTextView.text = "(" + currentWeather.weather[0].desc + ")"
        setWeatherIconAndBackground(weatherTypeToInt[currentWeather.weather[0].main] ?: 0)

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
    }

    private fun getDayStringFromTimestamp(timestamp: Long): String {
        val sdf = SimpleDateFormat("EEEE", Locale.US)
        return sdf.format(Date(timestamp*1000))
    }

    private fun setWeatherIconAndBackground(weatherId: Int) {
        when(weatherId) {
            weatherTypeToInt["Rain"], weatherTypeToInt["Snow"], weatherTypeToInt["Thunderstorm"], weatherTypeToInt["Drizzle"] -> {
                backgroud.setBackgroundResource(R.drawable.rainy_background)
                weatherIconImageView.setImageResource(R.drawable.rainy_white)
                activity!!.window.setStatusBarColor(activity!!.resources.getColor(R.color.rainyColorLight))
            }
            weatherTypeToInt["Clear"] -> {
                backgroud.setBackgroundResource(R.drawable.sunny_background)
                weatherIconImageView.setImageResource(R.drawable.sunny_white)
                activity!!.window.setStatusBarColor(activity!!.resources.getColor(R.color.sunnyColorLight))
            }
            weatherTypeToInt["Clouds"] -> {
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