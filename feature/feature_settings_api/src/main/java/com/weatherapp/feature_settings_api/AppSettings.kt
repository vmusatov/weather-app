package com.weatherapp.feature_settings_api

import com.weatherapp.core_base.model.PressureUnit
import com.weatherapp.core_base.model.SpeedUnit
import com.weatherapp.core_base.model.TempUnit

data class AppSettings(
    val tempUnit: TempUnit,
    val pressureUnit: PressureUnit,
    val speedUnit: SpeedUnit
) {
    companion object {
        fun default(): AppSettings = AppSettings(
            tempUnit = TempUnit.DEFAULT,
            pressureUnit = PressureUnit.DEFAULT,
            speedUnit = SpeedUnit.DEFAULT
        )
    }
}
