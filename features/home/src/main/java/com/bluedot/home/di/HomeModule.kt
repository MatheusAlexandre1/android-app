package com.bluedot.home.di

import com.bluedot.home.ui.favorites.FavoritesViewModel
import com.bluedot.home.ui.home.HomeViewModel
import com.bluedot.home.ui.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(repository = get()) }
    viewModel { SearchViewModel(repository = get()) }
    viewModel { FavoritesViewModel(repository = get()) }
}