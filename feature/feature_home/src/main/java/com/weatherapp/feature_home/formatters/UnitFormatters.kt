package com.weatherapp.feature_home.formatters

import com.weatherapp.core_base.model.PressureUnit
import com.weatherapp.core_base.model.SpeedUnit
import com.weatherapp.core_base.model.TempUnit
import kotlin.math.roundToInt

fun formatTemp(tempC: Int, unit: TempUnit): Int = when(unit) {
    TempUnit.C -> tempC
    TempUnit.F -> (tempC * 1.8 + 32).roundToInt()
}

fun formatSpeed(speedKmh: Int, unit: SpeedUnit): Int = when(unit) {
    SpeedUnit.KMH -> speedKmh
    SpeedUnit.MS -> (speedKmh / 3.6).roundToInt()
}

fun formatPressure(pressureMbar: Int, unit: PressureUnit): Int = when(unit) {
    PressureUnit.MBAR -> pressureMbar
    PressureUnit.MMHG ->  (pressureMbar / 1.333).roundToInt()
}