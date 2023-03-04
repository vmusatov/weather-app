package com.weatherapp.feature_home.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.weatherapp.core_design_system.component.*
import com.weatherapp.core_design_system.screen.AppScreen
import com.weatherapp.feature_home.R
import com.weatherapp.feature_home.presentation.components.*
import com.weatherapp.feature_home.presentation.model.*
import me.onebone.toolbar.*
import org.koin.androidx.compose.getViewModel

const val EXPAND_COLLAPSE_DURATION = 300

@Composable
fun HomeScreen(viewModel: HomeViewModel = getViewModel()) {
    val collapsingState = rememberCollapsingToolbarScaffoldState()

    AppScreen(
        viewModel = viewModel,
        reactToEvent = ::reactToEvent,
        topBar = { state ->
            HomeTopBar(
                toolbarState = collapsingState.toolbarState,
                state = state
            )
        },
        content = { paddings, state ->
            when (state) {
                is HomeState.Loading -> Loading()
                is HomeState.Content -> Content(
                    paddings = paddings,
                    collapsingState = collapsingState,
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
private fun HomeTopBar(
    toolbarState: CollapsingToolbarState,
    state: HomeState
) {
    val progress = toolbarState.progress
    val title = remember(state) {
        when (state) {
            is HomeState.Content -> state.location
            else -> ""
        }
    }

    AppTopBar(
        title = {
            LocationText(
                modifier = Modifier.alpha(1 - progress),
                text = title,
            )
        },
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
private fun Content(
    collapsingState: CollapsingToolbarScaffoldState,
    paddings: PaddingValues,
    state: HomeState.Content,
    viewModel: HomeViewModel
) {
    val scrollState = rememberScrollState()

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize().padding(paddings),
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        state = collapsingState,
        toolbar = {
            CollapsedContent(
                toolbarState = collapsingState.toolbarState,
                scrollState = scrollState,
                state = state
            )
        },
        body = {
            Column(
                modifier = Modifier.verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                HourlyCard(hourly = state.hourlyWeather)
                if (state.alerts.isNotEmpty()) {
                    AlertsCard(state = state)
                }
                DailyCard(daily = state.dailyWeather)
                SearchTextField(state, viewModel)
            }
        }
    )
}

@OptIn(ExperimentalToolbarApi::class)
@Composable
private fun CollapsingToolbarScope.CollapsedContent(
    toolbarState: CollapsingToolbarState,
    scrollState: ScrollState,
    state: HomeState.Content
) {
    val progress = toolbarState.progress

    LaunchedEffect(key1 = scrollState.isScrollInProgress, key2 = toolbarState.isScrollInProgress) {
        if (
            !toolbarState.isScrollInProgress && !scrollState.isScrollInProgress &&
            progress != 0.0f && progress != 1.0f
        ) {
            if (progress <= 0.5) {
                toolbarState.collapse(EXPAND_COLLAPSE_DURATION)
            }
            if (progress > 0.5) {
                toolbarState.expand(EXPAND_COLLAPSE_DURATION)
            }
        }
    }

    Spacer(modifier = Modifier)
    CurrentWeather(
        modifier = Modifier
            .alpha(toolbarState.progress)
            .parallax(ratio = 0.1f)
            .padding(
                start = (16 + progress * 10f).dp,
                end = (16 + progress * 10f).dp,
                bottom = 32.dp
            )
            .fillMaxWidth(),
        location = state.location,
        state = state
    )
}

@Composable
private fun CurrentWeather(
    modifier: Modifier,
    location: String,
    state: HomeState.Content
) {
    val current = state.currentWeather
    val today = state.dailyWeather.firstOrNull()

    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = current.temp,
                    style = MaterialTheme.typography.h2
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = if (today != null) "${today.maxTempText} / ${today.minTempText}" else "",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.W600
                    )
                )

                Text(
                    text = current.feelsLikeTemp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.W600
                    )
                )
            }
            Image(
                modifier = Modifier.size(120.dp),
                painter = painterResource(id = current.conditionIcon),
                contentDescription = null
            )
        }

        LocationText(
            modifier = Modifier.padding(top = 24.dp),
            text = location,
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.W500)
        )
    }
}

@Composable
private fun HourlyCard(hourly: HourlyWeatherUiModel) {
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
private fun AlertsCard(state: HomeState.Content) {
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
                            Modifier.padding(horizontal = 16.dp)
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
private fun DailyCard(daily: List<DailyWeatherUiModel>) {
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
private fun SearchTextField(state: HomeState.Content, viewModel: HomeViewModel) {
    val value = remember(state) { mutableStateOf(state.location) }
    Row(modifier = Modifier.padding(top = 150.dp)) {
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

@Preview
@Composable
private fun HomeScreenPreview() {
    val viewModel = HomeViewModelMock(isLoading = false)
    AppScreen(
        viewModel = viewModel,
        reactToEvent = { },
        topBar = { state ->
            HomeTopBar(
                state = state,
                toolbarState = CollapsingToolbarState()
            )
        },
        content = { paddings, state ->
            when (state) {
                is HomeState.Content -> Content(
                    paddings = paddings,
                    collapsingState = rememberCollapsingToolbarScaffoldState(),
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
        topBar = { state ->
            HomeTopBar(
                state = state,
                toolbarState = CollapsingToolbarState()
            )
        },
        content = { _, state ->
            when (state) {
                is HomeState.Loading -> Loading()
                else -> {}
            }
        }
    )
}