package com.weatherapp.feature_home.di

import com.weatherapp.feature_home.data.ForecastRepositoryImpl
import com.weatherapp.feature_home.domain.ForecastRepository
import com.weatherapp.feature_home.domain.mapper.ForecastMapper
import com.weatherapp.feature_home.presentation.HomeViewModel
import com.weatherapp.feature_home.presentation.HomeViewModelImpl
import com.weatherapp.feature_home.presentation.mapper.UiModelMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    single<ForecastRepository> { ForecastRepositoryImpl(get(), get()) }

    single { ForecastMapper() }
    single { UiModelMapper(get()) }

    viewModel<HomeViewModel> { HomeViewModelImpl() }
}