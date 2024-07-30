package com.nish.weatherapp

import android.app.Application
import com.nish.weatherapp.di.networkModule
import com.nish.weatherapp.di.repositoryModule
import com.nish.weatherapp.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Pass Android context
            androidContext(this@MainApplication)
            modules(vmModule, repositoryModule, networkModule)
        }
    }
}