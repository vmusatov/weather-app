package com.weatherapp.feature_home.domain.mapper

import com.weatherapp.core_network.model.ForecastDto
import com.weatherapp.feature_home.domain.model.DailyWeather
import com.weatherapp.feature_home.domain.model.Forecast
import com.weatherapp.feature_home.domain.model.HourlyWeather
import com.weatherapp.feature_home.domain.model.asDomain
import com.weatherapp.feature_locations.asDomain
import org.threeten.bp.LocalDateTime

class ForecastMapper {

    fun mapForecast(forecastDto: ForecastDto): Forecast {
        val location = forecastDto.location.asDomain()
        val dailyForecast = forecastDto
            .forecast
            .forecastDays
            .map { it.asDomain(forecastDto.location.timeZoneId) }

        return Forecast(
            location = location,
            current = forecastDto.current.asDomain(),
            dailyForecast = dailyForecast,
            hourlyForecast = parseDailyToHourlyForecast(location.localtime, dailyForecast),
            alerts = forecastDto.alerts.alert
                .distinctBy { it.desc }
                .filter { !it.desc.isNullOrEmpty() }
                .map { it.asDomain() },
            lastUpdated = null
        )
    }

    private fun parseDailyToHourlyForecast(
        localTime: LocalDateTime,
        dailyForecast: List<DailyWeather>
    ): List<HourlyWeather> {
        val hours = mutableListOf<HourlyWeather>()

        if (dailyForecast.isNotEmpty()) {

            val startHourAsInt = localTime.hour + 1
            val todayHours = dailyForecast.first().hours

            if (todayHours.size > startHourAsInt) {
                hours.addAll(todayHours.subList(startHourAsInt, todayHours.size))
            }

            if (dailyForecast.size > 1 && dailyForecast[1].hours.size >= startHourAsInt) {
                val tomorrowHours = dailyForecast[1].hours
                hours.addAll(tomorrowHours.subList(0, startHourAsInt))
            }
        }

        return hours
    }
}