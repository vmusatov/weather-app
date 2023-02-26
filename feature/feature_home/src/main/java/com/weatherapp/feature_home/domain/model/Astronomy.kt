package com.weatherapp.feature_home.domain.model

import com.weatherapp.core_network.model.AstronomyDto

data class Astronomy(
    val sunrise: String,
    val sunset: String
)

internal fun AstronomyDto.asDomain() = Astronomy(
    sunrise = astronomy.astro.sunrise,
    sunset = astronomy.astro.sunset
)