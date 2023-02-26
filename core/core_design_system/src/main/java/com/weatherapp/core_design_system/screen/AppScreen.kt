package com.weatherapp.core_design_system.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.weatherapp.core_base.mvi.Event
import com.weatherapp.core_base.mvi.UiState
import com.weatherapp.core_base.viewmodel.BaseViewModel
import com.weatherapp.core_design_system.theme.AppTheme

@Composable
fun <S : UiState, E : Event> AppScreen(
    viewModel: BaseViewModel<S, E>,
    reactToEvent: (E) -> Unit,
    paddingValues: PaddingValues = WindowInsets.systemBars.asPaddingValues(),
    topBar: @Composable (S) -> Unit = {},
    bottomBar: @Composable (S) -> Unit = {},
    floatingActionButton: @Composable (S) -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    content: @Composable (PaddingValues, S) -> Unit,
) {
    var state: S by remember(viewModel) { mutableStateOf(viewModel.initialState) }
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(viewModel) { viewModel.state.collect { state = it } }
    LaunchedEffect(viewModel) { viewModel.events.collect { reactToEvent(it) } }

    AppTheme {
        Scaffold(
            modifier = Modifier.padding(paddingValues),
            scaffoldState = scaffoldState,
            topBar = { topBar(state) },
            bottomBar = { bottomBar(state) },
            floatingActionButton = { floatingActionButton(state) },
            floatingActionButtonPosition = floatingActionButtonPosition
        ) { paddings ->
            content(paddings, state)
        }
    }
}
