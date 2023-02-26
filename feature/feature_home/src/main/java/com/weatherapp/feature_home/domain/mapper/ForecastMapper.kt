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
            lastUpdated = null
        )
    }

    private fun parseDailyToHourlyForecast(
        localTime: LocalDateTime,
        dailyForecast: List<DailyWeather>
    ): List<HourlyWeather> {
        val hours = mutableListOf<HourlyWeather>()

        if (dailyForecast.isNotEmpty()) {

            val nowHourAsInt = if (localTime.hour == 24) localTime.hour else localTime.hour + 1
            val todayHours = dailyForecast.first().hours

            if (todayHours.size > nowHourAsInt) {
                hours.addAll(todayHours.subList(nowHourAsInt, todayHours.size))
            }

            if (dailyForecast.size > 1 && dailyForecast[1].hours.size > nowHourAsInt) {
                val tomorrowHours = dailyForecast[1].hours
                hours.addAll(tomorrowHours.subList(0, nowHourAsInt))
            }
        }

        return hours
    }
}