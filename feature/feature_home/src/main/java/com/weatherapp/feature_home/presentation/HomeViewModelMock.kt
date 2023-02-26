package com.weatherapp.feature_home.presentation

import com.weatherapp.core_design_system.util.mapTempToColor
import com.weatherapp.feature_home.domain.model.Forecast
import com.weatherapp.feature_home.presentation.model.CurrentWeatherUiModel
import com.weatherapp.feature_home.presentation.model.HourlyWeatherItem
import com.weatherapp.feature_home.presentation.model.HourlyWeatherUiModel
import com.weatherapp.feature_settings_api.AppSettings
import kotlin.math.roundToInt
import kotlin.random.Random

class HomeViewModelMock(
    isLoading: Boolean
) : HomeViewModel(
    initialState = if (isLoading) HomeState.Loading else HomeState.Content(
        location = "London",
        currentWeather = CurrentWeatherUiModel.empty().copy(
            conditionText = "Sunny",
        ),
        hourlyWeather = getHourlyList(),
        dailyWeather = emptyList()
    )
) {
    private companion object {
        fun getHourlyList(): HourlyWeatherUiModel {
            val random = Random

            val list: MutableList<HourlyWeatherItem> = mutableListOf()

            for (i in 0..10) {
                val temp = random.nextInt(-5, 5).toFloat()
                list.add(
                    HourlyWeatherItem.empty().copy(
                        temp = temp,
                        startTemp = if (i == 0) null else list[i-1].temp,
                        endTemp = if (i == 10) null else temp,
                        tempText = "${temp.roundToInt()}°",
                        color = mapTempToColor(temp)
                    )
                )
            }

            return HourlyWeatherUiModel(
                items = list,
                maxTemp = list.maxBy { it.temp }.temp,
                minTemp = list.minBy { it.temp }.temp
            )
        }
    }

    override fun updateForecast(request: String) {}
}