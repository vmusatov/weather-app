package com.weatherapp.feature_home.domain.model

import com.weatherapp.core_base.model.TempUnit
import com.weatherapp.core_base.utils.toLocalDateTime
import com.weatherapp.core_network.model.ForecastDayDto
import com.weatherapp.feature_settings_api.AppSettings
import org.threeten.bp.LocalDateTime

data class DailyWeather(
    val date: LocalDateTime,
    val humidity: Int,
    val maxTempC: Double,
    val maxTempF: Double,
    val minTempC: Double,
    val minTempF: Double,
    val hours: List<HourlyWeather>
)

fun DailyWeather.maxTemp(settings: AppSettings): Double =
    if (settings.tempUnit == TempUnit.C) maxTempC else maxTempF

fun DailyWeather.minTemp(settings: AppSettings) =
    if (settings.tempUnit == TempUnit.C) minTempC else minTempF

internal fun ForecastDayDto.asDomain(timeZoneId: String) = DailyWeather(
    date = dateEpoch.toLocalDateTime(zoneId = timeZoneId),
    humidity = day.humidity,
    maxTempC = day.maxTempC,
    maxTempF = day.maxTempF,
    minTempC = day.minTempC,
    minTempF = day.minTempF,
    hours = hours.map { it.asDomain(timeZoneId) }
)