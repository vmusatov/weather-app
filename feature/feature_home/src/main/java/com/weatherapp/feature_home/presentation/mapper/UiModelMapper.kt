package com.weatherapp.feature_home.presentation.mapper

import android.content.Context
import com.weatherapp.core_base.utils.DATE_TIME_UI_FORMAT
import com.weatherapp.core_base.utils.HOUR_AND_MINUTE_FORMAT
import com.weatherapp.core_base.utils.dayOfWeek
import com.weatherapp.core_base.utils.format
import com.weatherapp.core_design_system.util.mapIconCode
import com.weatherapp.core_design_system.util.mapTempToColor
import com.weatherapp.feature_home.R
import com.weatherapp.feature_home.domain.model.*
import com.weatherapp.feature_home.presentation.model.*
import com.weatherapp.feature_settings_api.AppSettings
import org.threeten.bp.LocalDateTime
import kotlin.math.min
import kotlin.math.roundToInt
import com.weatherapp.core_design_system.R as CoreR

class UiModelMapper(private val context: Context) {

    fun mapCurrentWeather(
        current: CurrentWeather,
        settings: AppSettings
    ): CurrentWeatherUiModel {
        val temp = current.temp(settings).roundToInt()
        val feelsLikeTemp = current.feelsLikeTemp(settings).roundToInt()

        return CurrentWeatherUiModel(
            temp = context.getString(CoreR.string.degree, temp),
            feelsLikeTemp = context.getString(CoreR.string.feels_like, feelsLikeTemp),
            conditionIcon = mapIconCode(current.conditionIconCode, current.isDayIcon),
            conditionText = current.conditionText
        )
    }

    fun mapDailyForecast(
        daily: List<DailyWeather>,
        localDateTime: LocalDateTime,
        settings: AppSettings
    ): List<DailyWeatherUiModel> {
        return daily.map { item ->
            val maxTemp = item.maxTemp(settings).roundToInt()
            val minTemp = item.minTemp(settings).roundToInt()
            val dayText = if (item.date.toLocalDate().isEqual(localDateTime.toLocalDate())) {
                context.getString(R.string.today)
            } else {
                item.date.dayOfWeek()
            }

            val commonDayCondition = item.hours
                .subList(min(item.hours.size - 1, 6), min(item.hours.size - 1, 24))
                .groupBy { it.conditionIconCode }
                .maxBy { it.value.size }
                .key

            DailyWeatherUiModel(
                date = item.date,
                dayText = dayText,
                dayShortText = "",
                humidity = item.humidity,
                maxTemp = maxTemp,
                maxTempColor = mapTempToColor(item.maxTempC.toFloat()),
                maxTempText = context.getString(CoreR.string.degree, maxTemp),
                minTemp = minTemp,
                minTempColor = mapTempToColor(item.minTempC.toFloat()),
                minTempText = context.getString(CoreR.string.degree, minTemp),
                conditionIcon = mapIconCode(commonDayCondition, true),
                hours = mapHourlyForecast(item.hours, settings)
            )
        }
    }

    fun mapHourlyForecast(
        hourly: List<HourlyWeather>,
        settings: AppSettings
    ): HourlyWeatherUiModel {

        val items = hourly.mapIndexed { index, item ->
            val curTemp = item.temp(settings)
            HourlyWeatherItem(
                time = item.dateTime.format(HOUR_AND_MINUTE_FORMAT),
                temp = curTemp,
                startTemp = if (index == 0) null else hourly[index - 1].temp(settings),
                endTemp = if (index == hourly.size - 1) null else curTemp,
                tempText = context.getString(CoreR.string.degree, curTemp.roundToInt()),
                conditionIcon = mapIconCode(item.conditionIconCode, item.isDayIcon),
                humidity = "${item.humidity}%",
                color = mapTempToColor(item.tempC.toFloat())
            )
        }

        return HourlyWeatherUiModel(
            maxTemp = items.maxByOrNull { it.temp }?.temp ?: 0f,
            minTemp = items.minByOrNull { it.temp }?.temp ?: 0f,
            items = items,
        )
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
