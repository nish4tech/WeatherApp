package com.nish.weatherapp.di

import com.nish.weatherapp.data.remote.WeatherApi
import com.nish.weatherapp.data.remote.WeatherApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

const val BASE_URL = "api.open-meteo.com"

val httpEngine: HttpClientEngine by lazy {
    OkHttp.create {
        val networkInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        addNetworkInterceptor(networkInterceptor)
        //addInterceptor() todo we can add our Interceptor here
    }
}

val networkModule = module {
    single {
        HttpClient(engine = httpEngine) {
            expectSuccess = true
            install(HttpTimeout) {
                requestTimeoutMillis = 5 * 60 * 1000
                connectTimeoutMillis = 5 * 60 * 1000
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }
            install(DefaultRequest) {
                host = BASE_URL
                url {
                    protocol = URLProtocol.HTTPS
                }
                headers {
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                }
            }
            install(ResponseObserver) {
                onResponse { response ->
                    val responseStatus = response.status.value
                }
            }
        }
    }

    factory<WeatherApi> { WeatherApiImpl(get()) }
}