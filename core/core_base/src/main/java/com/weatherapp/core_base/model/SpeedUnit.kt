package com.weatherapp.core_base.model

enum class SpeedUnit(
    val unitName: String,
    val code: Int
) {
    KMH("km/h", 0),
    MS("m/s", 1);

    companion object {
        val DEFAULT = KMH

        fun fromCode(code: Int): SpeedUnit? {
            return SpeedUnit.values().firstOrNull { unit -> unit.code == code }
        }
    }
}