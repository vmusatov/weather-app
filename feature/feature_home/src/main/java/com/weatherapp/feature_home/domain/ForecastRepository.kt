package com.weatherapp.feature_home.domain

import com.weatherapp.core_base.result.WorkResult
import com.weatherapp.feature_home.domain.model.Forecast

interface ForecastRepository {

    suspend fun getForecast(query: String, lang: String): WorkResult<Forecast>
}