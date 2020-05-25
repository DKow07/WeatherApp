package com.example.weatherapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.Daily
import com.example.weatherapp.utils.WeatherAppUtils
import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastAdapter() : RecyclerView.Adapter<ForecastViewHolder>() {

    private val items = mutableListOf<Daily>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        if(itemCount > position) {
            holder.bindData(items[position])
        }
    }

    fun updateData(dataItems: List<Daily>) {
        items.clear()
        items.addAll(dataItems)
        notifyDataSetChanged()
    }
}

class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val dayName = view.itemDayName
    val mainTemp = view.itemMainTemp
    val smallIcon = view.itemSmallWeatherIcon
    val weatherType = view.itemWeatherType

    fun bindData(data: Daily) {
        dayName?.text = WeatherAppUtils.getDayStringFromTimestamp(data.dateTime)
        mainTemp?.text = (data.temp.day.toInt()).toString() + "\u00b0"
        weatherType?.text = data.weather[0].desc
        setSmallIcon(WeatherAppUtils.weatherTypeToInt[data.weather[0].main] ?: 0)
    }

    private fun setSmallIcon(weatherTypeId: Int) {
        when(weatherTypeId) {
            WeatherAppUtils.weatherTypeToInt["Rain"], WeatherAppUtils.weatherTypeToInt["Snow"]
                , WeatherAppUtils.weatherTypeToInt["Thunderstorm"], WeatherAppUtils.weatherTypeToInt["Drizzle"] -> {
                smallIcon.setImageResource(R.drawable.rainy_white_small)
            }
            WeatherAppUtils.weatherTypeToInt["Clear"] -> {
                smallIcon.setImageResource(R.drawable.sunny_white_small)
            }
            WeatherAppUtils.weatherTypeToInt["Clouds"] -> {
                smallIcon.setImageResource(R.drawable.cloudy_white_small)
            }
        }
    }
}