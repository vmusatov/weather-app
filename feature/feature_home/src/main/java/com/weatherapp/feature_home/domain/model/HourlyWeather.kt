package com.weatherapp.feature_home.domain.model

import com.weatherapp.core_base.utils.toLocalDateTime
import com.weatherapp.core_network.model.HourWeatherDto
import org.threeten.bp.LocalDateTime
import kotlin.math.roundToInt

data class HourlyWeather(
    val dateTime: LocalDateTime,
    val tempC: Int,
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

internal fun HourWeatherDto.asDomain(timeZoneId: String) = HourlyWeather(
    dateTime = timeEpoch.toLocalDateTime(zoneId = timeZoneId),
    tempC = tempC.roundToInt(),
    conditionText = condition.text,
    conditionIcon = condition.icon,
    conditionIconCode = condition.code,
    willItRain = willItRain,
    chanceOfRain = chanceOfRain,
    willItShow = willItShow,
    chanceOfSnow = chanceOfSnow,
    humidity = humidity
)