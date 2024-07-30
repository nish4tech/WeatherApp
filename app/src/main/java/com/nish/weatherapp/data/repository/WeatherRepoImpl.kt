package com.nish.weatherapp.data.repository

import com.nish.weatherapp.data.mapper.toWeatherInfo
import com.nish.weatherapp.data.remote.WeatherApi
import com.nish.weatherapp.data.remote.WeatherDto
import com.nish.weatherapp.domain.repository.WeatherRepo
import com.nish.weatherapp.domain.weather.WeatherInfo

class WeatherRepoImpl(private val weatherApi: WeatherApi): WeatherRepo {
    override suspend fun getWeatherData(lat: Double, long: Double): WeatherInfo {
        return weatherApi.getWeatherData(lat, long).toWeatherInfo()
    }
}