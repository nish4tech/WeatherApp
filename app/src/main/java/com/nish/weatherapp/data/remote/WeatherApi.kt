package com.nish.weatherapp.data.remote

import io.ktor.client.HttpClient

interface WeatherApi {
    suspend fun getWeatherData(lat: Double, long: Double): WeatherDto
}