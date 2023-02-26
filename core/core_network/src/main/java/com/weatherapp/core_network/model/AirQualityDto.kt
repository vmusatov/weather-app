package com.weatherapp.core_network.model

import com.google.gson.annotations.SerializedName

data class AirQualityDto(
    val co: Double,
    val no2: Double,
    val o3: Double,
    val so2: Double,
    @SerializedName("us-epa-index")
    val usEpaIndex: Int
)
