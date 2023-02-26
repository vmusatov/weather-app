package com.weatherapp.feature_home.presentation.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import org.threeten.bp.LocalDateTime

data class DailyWeatherUiModel(
    val date: LocalDateTime,
    val dayText: String,
    val dayShortText: String,
    val humidity: Int,
    val maxTemp: Int,
    val maxTempColor: Color,
    val maxTempText: String,
    val minTemp: Int,
    val minTempColor: Color,
    val minTempText: String,
    @DrawableRes
    val conditionIcon: Int,
    val hours: HourlyWeatherUiModel
)