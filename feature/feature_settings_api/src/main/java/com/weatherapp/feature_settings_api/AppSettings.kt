package com.weatherapp.feature_settings_api

import com.weatherapp.core_base.model.TempUnit

data class AppSettings(
    val tempUnit: TempUnit
) {
    companion object {
        fun default(): AppSettings = AppSettings(
            tempUnit = TempUnit.DEFAULT
        )
    }
}
