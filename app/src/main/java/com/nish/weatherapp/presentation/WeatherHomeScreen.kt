package com.nish.weatherapp.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nish.weatherapp.domain.weather.WeatherData
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherHomeScreen() {
    val viewModel = koinViewModel<WeatherScreenViewModel>()
    val launches by viewModel.launchState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        when (launches) {
            is WeatherInfoScreenState.Success -> {
                val data = (launches as WeatherInfoScreenState.Success).data.currentWeatherData
                data?.let { weatherData ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 50.dp)
                        ) {
                        WeatherCard(data = weatherData)
                    }
                }
            }

            is WeatherInfoScreenState.Error -> {
                val errorMessage = launches as WeatherInfoScreenState.Error
                Text(
                    text = errorMessage.exceptionMessage,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun ScreenUI(scope: BoxScope, data: WeatherData) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue)) {
        Text(text = data.time.toString(), color = Color.Green)
    }
}