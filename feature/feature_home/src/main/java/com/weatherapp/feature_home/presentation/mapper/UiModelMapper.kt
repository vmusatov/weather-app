package com.weatherapp.feature_home.presentation.mapper

import android.content.Context
import com.weatherapp.core_base.utils.DATE_TIME_UI_FORMAT
import com.weatherapp.core_base.utils.HOUR_AND_MINUTE_FORMAT
import com.weatherapp.core_base.utils.dayOfWeek
import com.weatherapp.core_base.utils.format
import com.weatherapp.core_design_system.util.mapIconCode
import com.weatherapp.core_design_system.util.mapTempToColor
import com.weatherapp.feature_home.R
import com.weatherapp.feature_home.domain.model.Alert
import com.weatherapp.feature_home.domain.model.CurrentWeather
import com.weatherapp.feature_home.domain.model.DailyWeather
import com.weatherapp.feature_home.domain.model.HourlyWeather
import com.weatherapp.feature_home.formatters.*
import com.weatherapp.feature_home.presentation.model.*
import com.weatherapp.feature_settings_api.AppSettings
import org.threeten.bp.LocalDateTime

class UiModelMapper(private val context: Context) {

    fun mapCurrentWeather(
        current: CurrentWeather,
        settings: AppSettings
    ): CurrentWeatherUiModel {
        return CurrentWeatherUiModel(
            temp = formatTempString(context, current.tempC, settings.tempUnit),
            feelsLikeTemp = formatFeelsLikeTempString(context, current.feelsLikeTempC, settings.tempUnit),
            conditionIcon = mapIconCode(current.conditionIconCode, current.isDayIcon),
            conditionText = current.conditionText,
            wind = formatSpeedString(context, current.windKph, settings.speedUnit),
            pressure = formatPressureString(context, current.pressureMb, settings.pressureUnit),
            uvIndex = formatUvIndex(context, current.uvIndex)
        )
    }

    fun mapDailyForecast(
        daily: List<DailyWeather>,
        localDateTime: LocalDateTime,
        settings: AppSettings
    ): List<DailyWeatherUiModel> {
        return daily.map { item ->

            val dayText = if (item.date.toLocalDate().isEqual(localDateTime.toLocalDate())) {
                context.getString(R.string.today)
            } else {
                item.date.dayOfWeek()
            }

            DailyWeatherUiModel(
                date = item.date,
                dayText = dayText,
                dayShortText = "",
                humidity = item.humidity,
                maxTemp = formatTemp(item.maxTempC, settings.tempUnit),
                maxTempColor = mapTempToColor(item.maxTempC),
                maxTempText = formatTempString(context, item.maxTempC, settings.tempUnit),
                minTemp = formatTemp(item.minTempC, settings.tempUnit),
                minTempColor = mapTempToColor(item.minTempC),
                minTempText = formatTempString(context, item.minTempC, settings.tempUnit),
                conditionIcon = mapIconCode(item.dayCondition, true),
                hours = mapHourlyForecast(item.hours, settings)
            )
        }
    }

    fun mapHourlyForecast(
        hourly: List<HourlyWeather>,
        settings: AppSettings
    ): HourlyWeatherUiModel {

        val items = hourly.mapIndexed { index, item ->
            val curTemp = formatTemp(item.tempC, settings.tempUnit)

            val prev = hourly.getOrNull(index - 1)
                ?.let { formatTemp(it.tempC, settings.tempUnit) }

            val next = hourly.getOrNull(index + 1)
                ?.let { formatTemp(it.tempC, settings.tempUnit) }

            HourlyWeatherItem(
                time = item.dateTime.format(HOUR_AND_MINUTE_FORMAT),
                temp = curTemp,
                startTemp = getStartTemp(curTemp, prev),
                endTemp = getEndTemp(curTemp, next),
                tempText = formatTempString(context, item.tempC, settings.tempUnit),
                conditionIcon = mapIconCode(item.conditionIconCode, item.isDayIcon),
                humidity = "${item.humidity}%",
                color = mapTempToColor(item.tempC)
            )
        }

        val max = items.maxByOrNull { it.temp }?.temp
        val min = items.minByOrNull { it.temp }?.temp

        return HourlyWeatherUiModel(
            maxTemp = max?.let { formatTemp(it, settings.tempUnit) } ?: 0,
            minTemp = min?.let { formatTemp(it, settings.tempUnit) } ?: 0,
            items = items,
        )
    }

    private fun getStartTemp(curTemp: Int, prevTemp: Int?): Int? {
        return if (prevTemp != null && prevTemp <= curTemp) curTemp else prevTemp
    }

    private fun getEndTemp(curTemp: Int, nextTemp: Int?): Int {
        return if (nextTemp != null && nextTemp > curTemp) nextTemp else curTemp
    }

    fun mapAlerts(alerts: List<Alert>): List<AlertUiModel> = alerts.map {
        AlertUiModel(
            headline = it.headline,
            msgType = it.msgType,
            severity = it.severity,
            urgency = it.urgency,
            areas = it.areas,
            category = it.category,
            certainty = it.certainty,
            event = it.event,
            note = it.note,
            effective = it.effective?.format(DATE_TIME_UI_FORMAT) ?: "",
            expires = it.expires?.format(DATE_TIME_UI_FORMAT) ?: "",
            desc = it.desc,
            instruction = it.instruction
        )
    }
}
