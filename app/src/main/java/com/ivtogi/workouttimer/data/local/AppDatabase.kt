package com.ivtogi.workouttimer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ivtogi.workouttimer.data.local.dao.TimerDao
import com.ivtogi.workouttimer.data.local.entities.TimerEntity

@Database(entities = [TimerEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun timerDao(): TimerDao
}