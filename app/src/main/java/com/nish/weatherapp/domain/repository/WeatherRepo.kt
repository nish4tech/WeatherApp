package com.nish.weatherapp.domain.repository

import com.nish.weatherapp.domain.weather.WeatherInfo

interface WeatherRepo {
    suspend fun getWeatherData(lat: Double, long: Double): WeatherInfo
}