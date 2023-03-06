package com.weatherapp.feature_home.domain.model

import com.weatherapp.core_network.model.CurrentWeatherDto
import kotlin.math.roundToInt

data class CurrentWeather(
    val tempC: Int,
    val feelsLikeTempC: Int,
    val conditionIcon: String,
    val conditionIconCode: Int,
    val conditionText: String,
    val windKph: Int,
    val uvIndex: Int,
    val pressureMb: Int,
    val co: Int,
    val no2: Int,
    val o3: Int,
    val so2: Int,
    val usEpaIndex: Int
) {
    val isDayIcon: Boolean
        get() = conditionIcon.isEmpty() || conditionIcon.lowercase().contains("day")
}

internal fun CurrentWeatherDto.asDomain() = CurrentWeather(
    tempC = tempC.roundToInt(),
    feelsLikeTempC = feelsLikeTempC.roundToInt(),
    conditionIcon = condition.icon,
    conditionIconCode = condition.code,
    conditionText = condition.text,
    windKph = windKph.roundToInt(),
    uvIndex = uvIndex,
    pressureMb = pressureMb.roundToInt(),
    co = airQuality.co.roundToInt(),
    no2 = airQuality.no2.roundToInt(),
    o3 = airQuality.o3.roundToInt(),
    so2 = airQuality.so2.roundToInt(),
    usEpaIndex = airQuality.usEpaIndex
)