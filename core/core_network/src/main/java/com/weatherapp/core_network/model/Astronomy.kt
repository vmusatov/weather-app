package com.weatherapp.core_network.model

import com.google.gson.annotations.SerializedName

data class AstronomyDto(
    @SerializedName("location")
    val location: LocationDto,
    @SerializedName("astronomy")
    val astronomy: AstronomyData
)

data class AstronomyData(
    @SerializedName("astro")
    val astro: Astro
)

data class Astro(
    @SerializedName("sunrise")
    val sunrise: String,
    @SerializedName("sunset")
    val sunset: String
)
