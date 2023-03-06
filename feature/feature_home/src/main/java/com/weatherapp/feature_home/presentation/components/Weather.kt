package com.weatherapp.feature_home.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.weatherapp.feature_home.presentation.model.AlertUiModel
import com.weatherapp.feature_home.presentation.model.DailyWeatherUiModel
import com.weatherapp.feature_home.presentation.model.HourlyWeatherItem

@Composable
fun TempDiapason(
    modifier: Modifier = Modifier,
    daily: DailyWeatherUiModel
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.defaultMinSize(minWidth = 35.dp),
            textAlign = TextAlign.End,
            text = daily.maxTempText
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .height(10.dp)
                .width((screenWidth / 4.5).dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(daily.maxTempColor, daily.minTempColor)
                    )
                )
        )

        Text(
            modifier = Modifier.defaultMinSize(minWidth = 35.dp),
            textAlign = TextAlign.Start,
            text = daily.minTempText
        )
    }
}

@Composable
fun AlertItem(modifier: Modifier = Modifier, alert: AlertUiModel) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = alert.event,
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.W600)
        )
        Text(
            text = alert.desc,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.secondaryVariant)
        )
    }
}

@Composable
fun DailyItem(daily: DailyWeatherUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier
                .defaultMinSize(minWidth = 110.dp)
                .padding(start = 16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.W500),
            text = daily.dayText
        )

        Image(
            modifier = Modifier
                .padding(end = 0.dp)
                .padding(vertical = 4.dp)
                .size(25.dp),
            painter = painterResource(id = daily.conditionIcon),
            contentDescription = null
        )
        TempDiapason(
            modifier = Modifier.padding(end = 8.dp),
            daily = daily
        )
    }
}

@Composable
fun HourlyItem(min: Int, max: Int, item: HourlyWeatherItem) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = item.time,
            style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.W500)
        )

        Image(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .size(25.dp),
            painter = painterResource(id = item.conditionIcon),
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            text = item.tempText,
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.W500)
        )

        HourlyGraphItem(
            modifier = Modifier
                .padding(top = 4.dp)
                .height(70.dp)
                .width(45.dp),
            valuesRange = min..max,
            startValue = item.startTemp,
            value = item.temp,
            endValue = item.endTemp,
            fillColor = item.color
        )

        HumidityText(
            modifier = Modifier.padding(top = 4.dp),
            value = item.humidity
        )
    }
}


@Composable
fun AdditionalWeatherItem(
    modifier: Modifier = Modifier,
    @DrawableRes
    icon: Int,
    name: String,
    value: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(50.dp),
            painter = painterResource(id = icon),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.W500),
            text = name
        )
        Text(
            text = value,
            style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.secondaryVariant)
        )
    }
}