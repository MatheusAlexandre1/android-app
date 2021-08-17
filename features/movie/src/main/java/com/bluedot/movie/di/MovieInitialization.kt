package com.bluedot.movie.di

import com.bluedot.movie.ui.details.MovieDetailsViewModel
import com.bluedot.movie.ui.list.MovieListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    viewModel { MovieListViewModel(repository = get()) }
    viewModel { MovieDetailsViewModel(repository = get()) }
}