package com.weatherapp.feature_home.presentation.model

data class AlertUiModel(
    val headline: String,
    val msgType: String,
    val severity: String,
    val urgency: String,
    val areas: String,
    val category: String,
    val certainty: String,
    val event: String,
    val note: String,
    val effective: String,
    val expires: String,
    val desc: String,
    val instruction: String
)
