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
                conditionIcon = mapIconCode(getCommonDayCondition(item.hours), true),
                hours = mapHourlyForecast(item.hours, settings)
            )
        }
    }

    private fun getCommonDayCondition(hours: List<HourlyWeather>): Int {
        val countMap = mutableMapOf<Int, Int>()
        var maxCount = 0
        var maxCondition = 0

        for (hour in hours) {
            val hourIndex = hours.indexOf(hour)
            if (hourIndex in 8..21) {
                continue
            }

            val condition = hour.conditionIconCode
            val count = (countMap[condition] ?: 0) + if (hour.isHavePrecipitation()) 3 else 1
            countMap[condition] = count

            if (count > maxCount) {
                maxCount = count
                maxCondition = condition
            }
        }

        return maxCondition
    }

    fun mapHourlyForecast(
        hourly: List<HourlyWeather>,
        settings: AppSettings
    ): HourlyWeatherUiModel {

        val items = hourly.mapIndexed { index, item ->
            val curTemp = item.temp(settings)
            val prev = hourly.getOrNull(index - 1)?.temp(settings)
            val next = hourly.getOrNull(index + 1)?.temp(settings)

            HourlyWeatherItem(
                time = item.dateTime.format(HOUR_AND_MINUTE_FORMAT),
                temp = curTemp,
                startTemp = getStartTemp(curTemp, prev),
                endTemp = getEndTemp(curTemp, next),
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

    private fun getStartTemp(curTemp: Float, prevTemp: Float?): Float? {
        return if (prevTemp != null && prevTemp <= curTemp) curTemp else prevTemp
    }

    private fun getEndTemp(curTemp: Float, nextTemp: Float?): Float? {
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
