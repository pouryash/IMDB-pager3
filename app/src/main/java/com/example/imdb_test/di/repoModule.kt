package com.example.imdb_test.di

import com.example.imdb_test.data.repo.DiscoverMovieRepository
import com.example.imdb_test.utils.SafeApiCaller
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repoModule = module {

single { DiscoverMovieRepository(get(), Dispatchers.IO, SafeApiCaller()) }

}