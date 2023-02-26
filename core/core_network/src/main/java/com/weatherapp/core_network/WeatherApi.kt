package com.weatherapp.core_network

import com.weatherapp.core_base.result.WorkResult
import com.weatherapp.core_network.model.AstronomyDto
import com.weatherapp.core_network.model.CurrentWeatherDto
import com.weatherapp.core_network.model.ForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    private val KEY: String
        get() = BuildConfig.WEATHER_API_KEY

    @GET("current.json")
    suspend fun getCurrent(
        @Query("q") q: String,
        @Query("aqi") aqi: String = "yes",
        @Query("lang") lang: String = "ru",
        @Query("key") key: String = KEY,
    ): WorkResult<CurrentWeatherDto>

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("q") q: String,
        @Query("days") days: Int = 7,
        @Query("aqi") aqi: String = "yes",
        @Query("alerts") alerts: String = "yes",
        @Query("lang") lang: String = "ru",
        @Query("key") key: String = KEY
    ): WorkResult<ForecastDto>

    @GET("astronomy.json")
    suspend fun getAstronomy(
        @Query("q") q: String,
        @Query("dt") dt: String,
        @Query("key") key: String = KEY,
    ): WorkResult<AstronomyDto>

}