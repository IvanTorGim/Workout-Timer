package com.ivtogi.workouttimer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ivtogi.workouttimer.data.database.dao.TimerDao
import com.ivtogi.workouttimer.data.database.entities.Exercise
import com.ivtogi.workouttimer.data.database.entities.Timer

@Database(entities = [Timer::class, Exercise::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun timerDao(): TimerDao
}