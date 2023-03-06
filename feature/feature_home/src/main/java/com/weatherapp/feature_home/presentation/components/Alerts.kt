package com.weatherapp.feature_home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.weatherapp.core_design_system.component.PagerIndicator
import com.weatherapp.feature_home.presentation.model.AlertUiModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AlertsContent(pagerState: PagerState, alerts: List<AlertUiModel>) {
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