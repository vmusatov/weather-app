package com.weatherapp.feature_home.presentation

import com.weatherapp.core_design_system.R
import com.weatherapp.core_design_system.util.mapTempToColor
import com.weatherapp.feature_home.presentation.model.*
import org.threeten.bp.LocalDateTime
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
        dailyWeather = getDailyList(),
        alerts = getAlertsList()
    )
) {
    private companion object {

        private fun getAlertsList(): List<AlertUiModel> {
            return listOf(
                AlertUiModel(
                    headline = "",
                    msgType = "",
                    severity = "",
                    urgency = "",
                    areas = "",
                    category = "",
                    certainty = "",
                    event = "Weather event",
                    note = "",
                    effective = "",
                    expires = "",
                    desc = "weather alerts description",
                    instruction = "",
                )
            )
        }

        private fun getDailyList(): List<DailyWeatherUiModel> {
            val result = mutableListOf<DailyWeatherUiModel>()

            for (i in 0..6) {
                result.add(randomDailyWeatherUiModel())
            }
            return result
        }

        private fun randomDailyWeatherUiModel(): DailyWeatherUiModel {
            val random = Random
            val maxTemp = random.nextInt(5, 20)
            val minTemp = random.nextInt(-15, 5)

            return DailyWeatherUiModel(
                date = LocalDateTime.MIN,
                dayText = "Today",
                dayShortText = "mn",
                humidity = 0,
                maxTemp = maxTemp,
                minTemp = minTemp,
                maxTempText = "$maxTemp°",
                minTempText = "$minTemp°",
                maxTempColor = mapTempToColor(maxTemp),
                minTempColor = mapTempToColor(minTemp),
                conditionIcon = R.drawable.ic_1000_day,
                hours = getHourlyList()
            )
        }

        private fun getHourlyList(): HourlyWeatherUiModel {
            val random = Random

            val list: MutableList<HourlyWeatherItem> = mutableListOf()

            for (i in 0..10) {
                val temp = random.nextInt(5, 15)
                list.add(
                    HourlyWeatherItem.empty().copy(
                        temp = temp,
                        startTemp = if (i == 0) null else list[i - 1].temp,
                        endTemp = if (i == 10) null else temp,
                        tempText = "${temp}°",
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