package com.weatherapp.feature_home.domain.model

import com.weatherapp.feature_locations.Location

data class Forecast(
    val location: Location,
    val current: CurrentWeather,
    val hourlyForecast: List<HourlyWeather>,
    val dailyForecast: List<DailyWeather>,
    val alerts: List<Alert>,
    val lastUpdated: String?
)