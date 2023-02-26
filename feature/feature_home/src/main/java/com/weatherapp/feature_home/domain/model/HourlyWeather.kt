package com.weatherapp.feature_home.domain.model

import com.weatherapp.core_base.model.TempUnit
import com.weatherapp.core_base.utils.toLocalDateTime
import com.weatherapp.core_network.model.HourWeatherDto
import com.weatherapp.feature_settings_api.AppSettings
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

data class HourlyWeather(
    val dateTime: LocalDateTime,
    val tempC: Double,
    val tempF: Double,
    val conditionText: String,
    val conditionIcon: String,
    val conditionIconCode: Int,
    val willItRain: Int,
    val chanceOfRain: Int,
    val chanceOfSnow: Int,
    val willItShow: Int,
    val humidity: Int
) {
    val isDayIcon: Boolean
        get() = conditionIcon.isEmpty() || conditionIcon.lowercase().contains("day")
}

fun HourlyWeather.isRain(): Boolean = willItRain != 0
        || (chanceOfRain > 50 && chanceOfRain > chanceOfSnow)
        || conditionText.lowercase().contains("rain")

fun HourlyWeather.isSnow(): Boolean = willItShow != 0
        || (chanceOfSnow > 50 && chanceOfSnow > chanceOfRain)
        || conditionText.lowercase().contains("snow")

fun HourlyWeather.isHavePrecipitation(): Boolean = isRain() || isSnow()

fun HourlyWeather.temp(settings: AppSettings) =
    if (settings.tempUnit == TempUnit.C) tempC.toFloat() else tempF.toFloat()

internal fun HourWeatherDto.asDomain(timeZoneId: String) = HourlyWeather(
    dateTime = timeEpoch.toLocalDateTime(zoneId = timeZoneId),
    tempC = tempC,
    tempF = tempF,
    conditionText = condition.text,
    conditionIcon = condition.icon,
    conditionIconCode = condition.code,
    willItRain = willItRain,
    chanceOfRain = chanceOfRain,
    willItShow = willItShow,
    chanceOfSnow = chanceOfSnow,
    humidity = humidity
)