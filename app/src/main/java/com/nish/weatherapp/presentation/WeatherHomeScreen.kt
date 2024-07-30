package com.nish.weatherapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherHomeScreen() {
    val viewModel = koinViewModel<WeatherScreenViewModel>()
    val launches by viewModel.launchState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when(launches) {
            is WeatherInfoScreenState.Success -> {
                Text(text = "Data came -> ${(launches as WeatherInfoScreenState.Success).data.currentWeatherData?.time}")
            }
            is WeatherInfoScreenState.Error -> {
                Text("Error")
            }
            else -> {
                Text("Loading")
            }
        }
        Spacer(Modifier.height(16.dp))
    }
}