package com.example.imdb_test.di

import com.example.imdb_test.presentation.DiscoverMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { DiscoverMovieViewModel(get()) }

}