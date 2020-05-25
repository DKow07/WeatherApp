package com.example.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.*

class WeatherAppUtils {

    companion object {

        val weatherTypeToInt = mapOf("Rain" to 1, "Clear" to 2, "Clouds" to 3, "Snow" to 4, "Thunderstorm" to 5, "Drizzle" to 6)


        fun getDayStringFromTimestamp(timestamp: Long): String {
            val sdf = SimpleDateFormat("EEEE", Locale.US)
            return sdf.format(Date(timestamp*1000))
        }
    }
}