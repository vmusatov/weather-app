package com.weatherapp.core_network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ForecastDto(
    @SerializedName("location")
    val location: LocationDto,
    @SerializedName("current")
    val current: CurrentWeatherDto,
    @SerializedName("forecast")
    val forecast: ForecastDaysDto,
    @SerializedName("alerts")
    val alerts: AlertsDto
)

data class ForecastDaysDto(
    @SerializedName("forecastday")
    val forecastDays: List<ForecastDayDto>
)

data class ForecastDayDto(
    @SerializedName("date_epoch")
    val dateEpoch: Long,
    @SerializedName("date")
    val date: String,
    @SerializedName("day")
    val day: DayWeatherDto,
    @SerializedName("hour")
    val hours: List<HourWeatherDto>
)

data class DayWeatherDto(
    @SerializedName("maxtemp_c")
    val maxTempC: Double,
    @SerializedName("maxtemp_f")
    val maxTempF: Double,
    @SerializedName("mintemp_c")
    val minTempC: Double,
    @SerializedName("" + "mintemp_f")
    val minTempF: Double,
    @SerializedName("condition")
    val condition: ConditionDto,
    @SerializedName("daily_chance_of_rain")
    val dailyChanceOfRain: Int,
    @SerializedName("daily_chance_of_snow")
    val dailyChanceOfSnow: Int,
    @SerializedName("avghumidity")
    val humidity: Int
)

data class HourWeatherDto(
    @SerializedName("time_epoch")
    val timeEpoch: Long,
    @SerializedName("time")
    val time: String,
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("temp_f")
    val tempF: Double,
    @SerializedName("condition")
    val condition: ConditionDto,
    @SerializedName("will_it_rain")
    val willItRain: Int,
    @SerializedName("chance_of_rain")
    val chanceOfRain: Int,
    @SerializedName("chance_of_snow")
    val chanceOfSnow: Int,
    @SerializedName("will_it_snow")
    val willItShow: Int,
    @SerializedName("humidity")
    val humidity: Int
)