package com.weatherapp.feature_home.presentation.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.weatherapp.core_design_system.theme.GrayB3
import com.weatherapp.core_design_system.R as CoreR

data class HourlyWeatherUiModel(
    val maxTemp: Float,
    val minTemp: Float,
    val items: List<HourlyWeatherItem>
)

data class HourlyWeatherItem(
    val time: String,
    val temp: Float,
    val startTemp: Float?,
    val endTemp: Float?,
    val tempText: String,
    @DrawableRes
    var conditionIcon: Int,
    val humidity: String,
    val color: Color
) {
    companion object {
        fun empty(): HourlyWeatherItem = HourlyWeatherItem(
            time = "00:00",
            temp = 0f,
            startTemp = 0f,
            endTemp = 0f,
            tempText = "",
            humidity = "0%",
            color = GrayB3,
            conditionIcon = CoreR.drawable.ic_1000_day
        )
    }
}
