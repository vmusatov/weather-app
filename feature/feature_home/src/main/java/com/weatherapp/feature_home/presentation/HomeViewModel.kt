package com.weatherapp.feature_home.presentation

import com.weatherapp.core_base.mvi.Event
import com.weatherapp.core_base.mvi.UiState
import com.weatherapp.core_base.viewmodel.BaseViewModel
import com.weatherapp.feature_home.domain.model.Forecast
import com.weatherapp.feature_home.presentation.model.CurrentWeatherUiModel
import com.weatherapp.feature_home.presentation.model.DailyWeatherUiModel
import com.weatherapp.feature_home.presentation.model.HourlyWeatherUiModel
import com.weatherapp.feature_settings_api.AppSettings

sealed class HomeEvent : Event

sealed class HomeState : UiState {

    object Loading : HomeState()

    data class Content(
        val location: String,
        val currentWeather: CurrentWeatherUiModel,
        val hourlyWeather: HourlyWeatherUiModel,
        val dailyWeather: List<DailyWeatherUiModel>,
    ) : HomeState()
}

abstract class HomeViewModel constructor(
    initialState: HomeState
) : BaseViewModel<HomeState, HomeEvent>(initialState) {

    abstract fun updateForecast(request: String = "arkhangelsk")
}