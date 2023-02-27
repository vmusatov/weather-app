package com.weatherapp.core_base.utils

import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.TextStyle
import java.util.*

const val ALERTS_FROM_SERVER_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX"
const val DATE_TIME_UI_FORMAT = "MM-dd HH:mm"
const val HOUR_AND_MINUTE_FORMAT = "HH:mm"

fun LocalDateTime.format(pattern: String): String {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
    return this.format(formatter)
}

fun LocalDateTime.dayOfWeek(locale: Locale = Locale.getDefault()): String {
    return this.dayOfWeek.getDisplayName(TextStyle.FULL_STANDALONE, locale)
}

fun Long.toLocalDateTime(zoneId: String): LocalDateTime = LocalDateTime.ofInstant(
    Instant.ofEpochSecond(this),
    ZoneId.of(zoneId)
)

fun String.toLocalDateTime(pattern: String): LocalDateTime {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
    return LocalDateTime.parse(this, formatter)
}
