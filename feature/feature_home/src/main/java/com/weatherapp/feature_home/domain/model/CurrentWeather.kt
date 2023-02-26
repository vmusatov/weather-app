package com.weatherapp.feature_home.domain.model

import com.weatherapp.core_base.model.TempUnit
import com.weatherapp.core_network.model.CurrentWeatherDto
import com.weatherapp.feature_settings_api.AppSettings

data class CurrentWeather(
    val tempC: Double,
    val tempF: Double,
    val feelsLikeTempC: Double,
    val feelsLikeTempF: Double,
    val conditionIcon: String,
    val conditionIconCode: Int,
    val conditionText: String,
    val windKph: Double,
    val uvIndex: Int,
    val pressureMb: Double,
    val co: Double,
    val no2: Double,
    val o3: Double,
    val so2: Double,
    val usEpaIndex: Int
) {
    val isDayIcon: Boolean
        get() = conditionIcon.isEmpty() || conditionIcon.lowercase().contains("day")
}

fun CurrentWeather.temp(settings: AppSettings): Float {
    return if (settings.tempUnit == TempUnit.C) tempC.toFloat() else tempF.toFloat()
}

fun CurrentWeather.feelsLikeTemp(settings: AppSettings): Float {
    return if (settings.tempUnit == TempUnit.C) feelsLikeTempC.toFloat() else feelsLikeTempF.toFloat()
}

internal fun CurrentWeatherDto.asDomain() = CurrentWeather(
    tempC = tempC,
    tempF = tempF,
    feelsLikeTempC = feelsLikeTempC,
    feelsLikeTempF = feelsLikeTempF,
    conditionIcon = condition.icon,
    conditionIconCode = condition.code,
    conditionText = condition.text,
    windKph = windKph,
    uvIndex = uvIndex,
    pressureMb = pressureMb,
    co = airQuality.co,
    no2 = airQuality.no2,
    o3 = airQuality.o3,
    so2 = airQuality.so2,
    usEpaIndex = airQuality.usEpaIndex
)