package com.weatherapp.feature_home.domain.model

import com.weatherapp.core_base.utils.ALERTS_FROM_SERVER_FORMAT
import com.weatherapp.core_network.model.AlertItemDto
import org.threeten.bp.LocalDateTime
import com.weatherapp.core_base.utils.toLocalDateTime

data class Alert(
    val headline: String,
    val msgType: String,
    val severity: String,
    val urgency: String,
    val areas: String,
    val category: String,
    val certainty: String,
    val event: String,
    val note: String,
    val effective: LocalDateTime?,
    val expires: LocalDateTime?,
    val desc: String,
    val instruction: String
)

internal fun AlertItemDto.asDomain() = Alert(
    headline = headline ?: "",
    msgType = msgType ?: "",
    severity = severity ?: "",
    urgency = urgency ?: "",
    areas = areas ?: "",
    category = category ?: "",
    certainty = certainty ?: "",
    event = event ?: "",
    note = note ?: "",
    effective = effective?.toLocalDateTime(ALERTS_FROM_SERVER_FORMAT),
    expires = expires?.toLocalDateTime(ALERTS_FROM_SERVER_FORMAT),
    desc = desc?.removePrefix("...")?.lowercase() ?: "",
    instruction = instruction ?: ""
)