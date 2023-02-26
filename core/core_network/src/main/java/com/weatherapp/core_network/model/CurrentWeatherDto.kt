package com.weatherapp.core_network.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherDto(
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("temp_f")
    val tempF: Double,
    @SerializedName("feelslike_c")
    val feelsLikeTempC: Double,
    @SerializedName("feelslike_f")
    val feelsLikeTempF: Double,
    @SerializedName("condition")
    val condition: ConditionDto,
    @SerializedName("wind_kph")
    val windKph: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("uv")
    val uvIndex: Int,
    @SerializedName("pressure_mb")
    val pressureMb: Double,
    @SerializedName("air_quality")
    val airQuality: AirQualityDto,
)