package com.weatherapp.feature_home.presentation

import androidx.lifecycle.viewModelScope
import com.weatherapp.core_base.result.handle
import com.weatherapp.feature_home.domain.ForecastRepository
import com.weatherapp.feature_home.domain.model.Forecast
import com.weatherapp.feature_home.presentation.mapper.UiModelMapper
import com.weatherapp.feature_settings_api.AppSettings
import com.weatherapp.feature_settings_api.SettingsRepository
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import java.util.*

class HomeViewModelImpl : HomeViewModel(initialState = HomeState.Loading) {

    private val forecastRepository: ForecastRepository by inject()
    private val settingsRepository: SettingsRepository by inject()
    private val mapper: UiModelMapper by inject()

    private var lastLoadedForecast: Forecast? = null

    init {
        updateForecast()
        observeSettings()
    }

    override fun updateForecast(request: String) {
        viewModelScope.launch {
            val settings = settingsRepository.getSettings()

            forecastRepository.getForecast(request, Locale.getDefault().language).handle(
                onSuccess = {
                    lastLoadedForecast = it
                    showForecast(it, settings)
                },
                onNotSuccess = {}
            )
        }
    }

    private fun observeSettings() = viewModelScope.launch {
        settingsRepository.getSettingsFlow().collect { updateSettings(it) }
    }

    private fun updateSettings(settings: AppSettings) {
        updateIfStateIs<HomeState.Content> { state ->
            lastLoadedForecast?.let { forecast ->
                showForecast(forecast, settings)
            }
            state
        }
    }

    private fun showForecast(forecast: Forecast, settings: AppSettings) = updateState {
        HomeState.Content(
            location = forecast.location.name,
            currentWeather = mapper.mapCurrentWeather(
                current = forecast.current,
                settings = settings
            ),
            hourlyWeather = mapper.mapHourlyForecast(
                hourly = forecast.hourlyForecast,
                settings = settings
            ),
            dailyWeather = mapper.mapDailyForecast(
                daily = forecast.dailyForecast,
                localDateTime = forecast.location.localtime,
                settings = settings
            )
        )
    }
}