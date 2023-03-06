package com.weatherapp.feature_home.formatters

import android.content.Context
import com.weatherapp.core_base.model.PressureUnit
import com.weatherapp.core_base.model.SpeedUnit
import com.weatherapp.core_base.model.TempUnit
import com.weatherapp.core_design_system.R as CoreR

fun formatTempString(context: Context, tempC: Int, unit: TempUnit): String {
    return when (unit) {
        TempUnit.C -> context.getString(CoreR.string.degree, formatTemp(tempC, unit))
        TempUnit.F -> context.getString(CoreR.string.degree, formatTemp(tempC, unit))
    }
}

fun formatFeelsLikeTempString(context: Context, tempC: Int, unit: TempUnit): String {
    return when (unit) {
        TempUnit.C -> context.getString(CoreR.string.feels_like, formatTemp(tempC, unit))
        TempUnit.F -> context.getString(CoreR.string.feels_like, formatTemp(tempC, unit))
    }
}

fun formatSpeedString(context: Context, speedKmh: Int, unit: SpeedUnit): String {
    return when (unit) {
        SpeedUnit.KMH -> context.getString(CoreR.string.kmh, formatSpeed(speedKmh, unit))
        SpeedUnit.MS -> context.getString(CoreR.string.ms, formatSpeed(speedKmh, unit))
    }
}

fun formatPressureString(context: Context, pressureMbar: Int, unit: PressureUnit): String {
    return when (unit) {
        PressureUnit.MBAR -> context.getString(CoreR.string.mbar, formatPressure(pressureMbar, unit))
        PressureUnit.MMHG -> context.getString(CoreR.string.mmhg, formatPressure(pressureMbar, unit))
    }
}

fun formatUvIndex(context: Context, index: Int): String {
    return when (index) {
        in 1..2 -> context.getString(CoreR.string.low)
        in 3..5 -> context.getString(CoreR.string.moderate)
        in 6..7 -> context.getString(CoreR.string.high)
        in 8..10 -> context.getString(CoreR.string.very_high)
        in 11..15 -> context.getString(CoreR.string.extreme)
        else -> context.getString(CoreR.string.undefined)
    }
}