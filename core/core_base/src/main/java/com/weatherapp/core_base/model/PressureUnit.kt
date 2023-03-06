package com.weatherapp.core_base.model

enum class PressureUnit(
    val unitName: String,
    val code: Int
) {
    MBAR("mar", 0),
    MMHG("mmhg", 1);

    companion object {
        val DEFAULT = MBAR

        fun fromCode(code: Int): PressureUnit? {
            return PressureUnit.values().firstOrNull { unit -> unit.code == code }
        }
    }
}