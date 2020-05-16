package com.example.weatherapp.service

interface RequestCallback<T> {
    fun onSuccess(data: T)

    fun onError(throwable: Throwable)

    fun onCancel()
}