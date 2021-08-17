package com.bluedot.data.di

import com.bluedot.data.preferences.MovieLogEncryptedPrefs
import com.bluedot.data.preferences.MovieLogPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferencesModule = module {
    single { MovieLogPrefs(androidContext()) }
    single { MovieLogEncryptedPrefs(androidContext()) }
}