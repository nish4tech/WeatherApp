package com.nish.weatherapp.di


import org.koin.androidx.viewmodel.dsl.viewModel
import com.nish.weatherapp.presentation.WeatherScreenViewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel {
        WeatherScreenViewModel(get())
    }
}