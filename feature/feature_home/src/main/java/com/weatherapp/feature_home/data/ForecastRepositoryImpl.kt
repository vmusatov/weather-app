package com.weatherapp.feature_home.data

import com.weatherapp.core_base.result.WorkResult
import com.weatherapp.core_base.result.map
import com.weatherapp.core_network.WeatherApi
import com.weatherapp.feature_home.domain.mapper.ForecastMapper
import com.weatherapp.feature_home.domain.model.Forecast
import com.weatherapp.feature_home.domain.ForecastRepository

class ForecastRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val mapper: ForecastMapper,
) : ForecastRepository {

    override suspend fun getForecast(query: String, lang: String): WorkResult<Forecast> {
        return weatherApi.getForecast(q = query, lang = lang).map(mapper::mapForecast)
    }
}