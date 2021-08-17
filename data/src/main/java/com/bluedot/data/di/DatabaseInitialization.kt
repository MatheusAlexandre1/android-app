package com.bluedot.data.di

import android.app.Application
import androidx.room.Room
import com.bluedot.data.database.MovieDao
import com.bluedot.data.database.MovieLogDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

const val DATABASE_NAME = "app-db"

val databaseModule = module {

    fun provideDatabase(application: Application): MovieLogDatabase {
        return Room.databaseBuilder(application, MovieLogDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: MovieLogDatabase): MovieDao {
        return database.movieDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}