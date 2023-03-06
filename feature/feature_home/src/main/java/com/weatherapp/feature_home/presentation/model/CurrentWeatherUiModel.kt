package com.weatherapp.feature_home.presentation.model

import androidx.annotation.DrawableRes
import com.weatherapp.core_design_system.R as CoreR

data class CurrentWeatherUiModel(
    val temp: String,
    val feelsLikeTemp: String,
    val wind: String,
    val uvIndex: String,
    val pressure: String,
    @DrawableRes
    val conditionIcon: Int,
    val conditionText: String,
) {
    companion object {
        fun empty() = CurrentWeatherUiModel(
            temp = "0°",
            feelsLikeTemp = "Feels like 0°",
            conditionIcon = CoreR.drawable.ic_1000_day,
            conditionText = "",
            wind = "",
            uvIndex = "",
            pressure = ""
        )
    }
}