package com.weatherapp.feature_home.presentation.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.weatherapp.core_design_system.theme.GrayB3
import com.weatherapp.core_design_system.R as CoreR

data class HourlyWeatherUiModel(
    val maxTemp: Int,
    val minTemp: Int,
    val items: List<HourlyWeatherItem>
)

data class HourlyWeatherItem(
    val time: String,
    val temp: Int,
    val startTemp: Int?,
    val endTemp: Int?,
    val tempText: String,
    @DrawableRes
    var conditionIcon: Int,
    val humidity: String,
    val color: Color
) {
    companion object {
        fun empty(): HourlyWeatherItem = HourlyWeatherItem(
            time = "00:00",
            temp = 0,
            startTemp = 0,
            endTemp = 0,
            tempText = "",
            humidity = "0%",
            color = GrayB3,
            conditionIcon = CoreR.drawable.ic_1000_day
        )
    }
}
