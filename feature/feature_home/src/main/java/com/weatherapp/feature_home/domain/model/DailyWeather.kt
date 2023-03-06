package com.weatherapp.feature_home.domain.model

import com.weatherapp.core_base.utils.toLocalDateTime
import com.weatherapp.core_network.model.ForecastDayDto
import org.threeten.bp.LocalDateTime
import kotlin.math.roundToInt

data class DailyWeather(
    val date: LocalDateTime,
    val humidity: Int,
    val maxTempC: Int,
    val minTempC: Int,
    val dayCondition: Int,
    val hours: List<HourlyWeather>
)

internal fun ForecastDayDto.asDomain(timeZoneId: String) = DailyWeather(
    date = dateEpoch.toLocalDateTime(zoneId = timeZoneId),
    humidity = day.humidity,
    maxTempC = day.maxTempC.roundToInt(),
    minTempC = day.minTempC.roundToInt(),
    dayCondition = day.condition.code,
    hours = hours.map { it.asDomain(timeZoneId) }
)