package com.nish.weatherapp.di

import com.nish.weatherapp.data.repository.WeatherRepoImpl
import com.nish.weatherapp.domain.repository.WeatherRepo
import org.koin.dsl.module

val repositoryModule = module {
    factory<WeatherRepo> {  WeatherRepoImpl(get()) }
}