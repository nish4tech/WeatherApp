package com.nish.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nish.weatherapp.domain.repository.WeatherRepo
import com.nish.weatherapp.domain.weather.WeatherInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class WeatherScreenViewModel(val weatherRepo: WeatherRepo): ViewModel() {
    private val _launchState = MutableStateFlow<WeatherInfoScreenState>(WeatherInfoScreenState.Uninitialized)
    val launchState: StateFlow<WeatherInfoScreenState> = _launchState

    init {
        loadWeatherData()
    }

    fun loadWeatherData() {
        _launchState.value = WeatherInfoScreenState.Loading

        viewModelScope.launch {
            try {
                _launchState.value = WeatherInfoScreenState.Success(weatherRepo.getWeatherData("43.466".toDouble(), "-79.69".toDouble()))
            } catch (e: Exception) {
                e.printStackTrace()
                _launchState.value = WeatherInfoScreenState.Error(e.message.orEmpty())
            }

        }
    }
}

sealed interface WeatherInfoScreenState {
    data class Success(val data: WeatherInfo) : WeatherInfoScreenState
    data class Error(val exceptionMessage: String) : WeatherInfoScreenState
    object Loading : WeatherInfoScreenState
    object Uninitialized : WeatherInfoScreenState
}