package com.weatherapp.feature_locations

import com.weatherapp.core_base.utils.toLocalDateTime
import com.weatherapp.core_network.model.LocationDto
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime
import java.util.*

data class Location(
    var name: String,
    var region: String,
    var country: String,
    var localtime: LocalDateTime,
    var lat: Double,
    var lon: Double,
    var isSelected: Boolean = false,
    var lastUpdated: Date? = null
)

fun LocationDto.asDomain() = Location(
    name = name,
    region = region,
    country = country,
    localtime = localtimeEpoch.toLocalDateTime(timeZoneId),
    lat = lat,
    lon = lon,
)