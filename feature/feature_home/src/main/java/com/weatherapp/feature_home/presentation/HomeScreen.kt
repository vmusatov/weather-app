package com.weatherapp.feature_home.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.weatherapp.core_design_system.component.*
import com.weatherapp.core_design_system.screen.AppScreen
import com.weatherapp.feature_home.R
import com.weatherapp.feature_home.presentation.components.HourlyGraphItem
import com.weatherapp.feature_home.presentation.components.HumidityText
import com.weatherapp.feature_home.presentation.model.*
import org.koin.androidx.compose.getViewModel
import kotlin.math.roundToInt

@Composable
fun HomeScreen(viewModel: HomeViewModel = getViewModel()) {
    AppScreen(
        viewModel = viewModel,
        reactToEvent = ::reactToEvent,
        topBar = { state -> HomeTopBar(state) },
        content = { paddings, state ->
            when (state) {
                is HomeState.Loading -> Loading()
                is HomeState.Content -> Content(
                    paddings = paddings,
                    state = state,
                    viewModel = viewModel
                )
            }
        }
    )
}

private fun reactToEvent(event: HomeEvent) {

}

@Composable
private fun HomeTopBar(state: HomeState) {
    val title = remember(state) {
        when (state) {
            is HomeState.Content -> state.location
            else -> ""
        }
    }

    AppTopBar(
        title = title,
        navigationIcon = Icons.Rounded.Menu,
        onNavIconClick = {},
        contentRight = {
            AppIconButton(
                icon = Icons.Rounded.Settings,
                onClick = { }
            )
        }
    )
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = {
            CircularProgressIndicator()
        }
    )
}

@Composable
private fun Content(paddings: PaddingValues, state: HomeState.Content, viewModel: HomeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddings)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Current(current = state.currentWeather)
        HourlyForecast(hourly = state.hourlyWeather)
        if (state.alerts.isNotEmpty()) {
            Alerts(state = state)
        }
        DailyForecast(daily = state.dailyWeather)
        SearchTextField(state, viewModel)
    }
}

@Composable
private fun SearchTextField(state: HomeState.Content, viewModel: HomeViewModel) {
    val value = remember(state) { mutableStateOf(state.location) }
    Row(modifier = Modifier.padding(top = 90.dp)) {
        TextField(
            value = value.value,
            onValueChange = { value.value = it }
        )
        Button(
            onClick = { viewModel.updateForecast(value.value) },
            content = { Text(text = "find") }
        )
    }
}

@Composable
private fun Current(current: CurrentWeatherUiModel) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(2f)) {
            Text(
                modifier = Modifier.padding(bottom = 32.dp),
                text = current.temp,
                style = MaterialTheme.typography.h2
            )
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = current.conditionText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body1
            )
            Text(
                modifier = Modifier.padding(bottom = 32.dp),
                text = current.feelsLikeTemp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.secondaryVariant)
            )
        }
        Row(
            modifier = Modifier.weight(1.2f),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                modifier = Modifier.size(120.dp),
                painter = painterResource(id = current.conditionIcon),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun HourlyForecast(hourly: HourlyWeatherUiModel) {
    val state = rememberLazyListState()
    LaunchedEffect(hourly) {
        state.scrollToItem(0)
    }
    AppCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        content = {
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 16.dp)
                    .fillMaxWidth(),
                state = state
            ) {
                items(hourly.items) {
                    HourlyItem(hourly.minTemp, hourly.maxTemp, it)
                }
            }
        }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Alerts(state: HomeState.Content) {
    val pagerState = remember(state) { PagerState() }
    AppCard(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        title = LocalContext.current.getString(R.string.alerts),
        titleContent = {
            AppIconButton(
                modifier = Modifier.size(20.dp),
                icon = Icons.Rounded.ArrowForward,
                onClick = { }
            )
        },
        content = {
            AlertsContent(
                pagerState = pagerState,
                alerts = state.alerts
            )
        }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun AlertsContent(pagerState: PagerState, alerts: List<AlertUiModel>) {
    Box(
        modifier = Modifier.clickable { }
    ) {
        Column(
            modifier = Modifier.padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                count = alerts.size,
                state = pagerState,
                content = { page ->
                    AlertItem(
                        modifier = if(alerts.size > 1)
                            Modifier.padding(start = 16.dp, end = 16.dp)
                        else
                            Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                        alert = alerts[page]
                    )
                }
            )
            if (alerts.size > 1) {
                PagerIndicator(
                    modifier = Modifier.padding(top = 8.dp),
                    pagerState = pagerState
                )
            }
        }
    }
}

@Composable
private fun AlertItem(modifier: Modifier = Modifier, alert: AlertUiModel) {
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
private fun DailyForecast(daily: List<DailyWeatherUiModel>) {
    val context = LocalContext.current
    AppCard(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        title = context.getString(R.string.days_forecast, daily.size),
        titleContent = {
            AppIconButton(
                modifier = Modifier.size(20.dp),
                icon = Icons.Rounded.ArrowForward,
                onClick = { }
            )
        },
        content = {
            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                daily.forEach { DailyItem(it) }
            }
        }
    )
}

@Composable
private fun DailyItem(daily: DailyWeatherUiModel) {
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
private fun TempDiapason(
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
            text = daily.minTempText
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .height(10.dp)
                .width((screenWidth / 4.5).dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(daily.minTempColor, daily.maxTempColor,)
                    )
                )
        )

        Text(
            modifier = Modifier.defaultMinSize(minWidth = 35.dp),
            textAlign = TextAlign.Start,
            text = daily.maxTempText
        )
    }
}

@Composable
private fun HourlyItem(min: Float, max: Float, item: HourlyWeatherItem) {
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
                .height(70.dp)
                .width(45.dp),
            valuesRange = min.roundToInt()..max.roundToInt(),
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

@Preview
@Composable
private fun HomeScreenPreview() {
    val viewModel = HomeViewModelMock(isLoading = false)
    AppScreen(
        viewModel = viewModel,
        reactToEvent = { },
        topBar = { state -> HomeTopBar(state) },
        content = { paddings, state ->
            when (state) {
                is HomeState.Content -> Content(
                    paddings = paddings,
                    state = state,
                    viewModel = viewModel
                )
                else -> {}
            }
        }
    )
}

@Preview
@Composable
private fun HomeScreenLoadingPreview() {
    AppScreen(
        viewModel = HomeViewModelMock(isLoading = true),
        reactToEvent = { },
        topBar = { state -> HomeTopBar(state) },
        content = { _, state ->
            when (state) {
                is HomeState.Loading -> Loading()
                else -> {}
            }
        }
    )
}