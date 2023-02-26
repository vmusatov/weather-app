package com.weatherapp.core_network

import com.weatherapp.core_base.result.WorkResult
import com.weatherapp.core_network.model.SearchLocationDto
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationsApi {

    private val KEY: String
        get() = BuildConfig.WEATHER_API_KEY

    @GET("search.json")
    suspend fun searchLocations(
        @Query("q") q: String,
        @Query("key") key: String = KEY,
    ): WorkResult<List<SearchLocationDto>>
}