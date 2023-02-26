package com.weatherapp.core_base.viewmodel

import androidx.lifecycle.ViewModel
import com.weatherapp.core_base.mvi.Event
import com.weatherapp.core_base.mvi.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.core.component.KoinComponent

abstract class BaseViewModel<S : UiState, E : Event>(
    val initialState: S
) : ViewModel(), KoinComponent {

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _events: Channel<E> = Channel(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    protected fun updateState(updater: (S) -> S) {
        _state.value = updater(_state.value)
    }

    protected inline fun <reified T : S> updateIfStateIs(crossinline updater: (T) -> S) {
        if (state.value is T) {
            updateState { updater(state.value as T) }
        }
    }


    protected fun sendEvent(event: E) {
        _events.trySend(event)
    }
}