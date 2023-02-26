package com.weatherapp.core_coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val coroutinesModule = module {

    single { CoroutineScope(SupervisorJob() + Dispatchers.Default) }

    single(named(DispatcherType.DEFAULT)) { Dispatchers.Default }
    single(named(DispatcherType.IO)) { Dispatchers.IO }
    single(named(DispatcherType.MAIN)) { Dispatchers.Main }

    single { Dispatchers.Default } bind CoroutineDispatcher::class
}

enum class DispatcherType {
    DEFAULT,
    IO,
    MAIN
}