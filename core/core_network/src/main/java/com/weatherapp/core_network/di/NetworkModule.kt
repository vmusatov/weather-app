package com.weatherapp.core_network.di

import com.weatherapp.core_network.WeatherApi
import com.weatherapp.core_network.adapter.ResultAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideWeatherApi(get()) }
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get()) }
    single { provideHttpLoggingInterceptor() }
}

private fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
    return retrofit.create(WeatherApi::class.java)
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(WEATHER_API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ResultAdapterFactory())
        .build()
}

private fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = LOGGING_LEVEL

    return httpLoggingInterceptor
}

private val LOGGING_LEVEL = HttpLoggingInterceptor.Level.BODY
