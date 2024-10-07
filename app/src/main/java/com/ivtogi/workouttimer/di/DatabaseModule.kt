package com.ivtogi.workouttimer.di

import android.content.Context
import androidx.room.Room
import com.ivtogi.workouttimer.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "WorkoutTimerDatabase"
        ).build()

    @Provides
    @Singleton
    fun providesTimerDao(database: AppDatabase) = database.timerDao()
}