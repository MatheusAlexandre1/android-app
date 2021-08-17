package com.bluedot.data.di

import com.bluedot.data.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MovieRepository(api = get(), dao = get()) }
}
