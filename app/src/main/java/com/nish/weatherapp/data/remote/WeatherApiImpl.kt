package com.nish.weatherapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class WeatherApiImpl(private val httpClient: HttpClient): WeatherApi {
    override suspend fun getWeatherData(lat: Double, long: Double): WeatherDto {
        val weatherUrl = "v1/forecast?latitude=${lat}&longitude=${long}&hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl"
        return httpClient.get(weatherUrl).body()
    }
}