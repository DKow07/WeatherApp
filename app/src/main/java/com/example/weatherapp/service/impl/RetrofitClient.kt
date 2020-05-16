package com.example.weatherapp.service.impl

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val CONNECTION_TIMEOUT = 10L;
    private val READ_TIMEOUT = 5L;
    private val WRITE_TIMEOUT = 5L;
    private val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private lateinit var retrofit: Retrofit

    init {
        initRetrofit()
    }

    private fun initRetrofit() {
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofit() = retrofit

}