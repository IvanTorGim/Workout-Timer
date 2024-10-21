package com.itortosagimeno.workouttimer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itortosagimeno.workouttimer.data.local.dao.TimerDao
import com.itortosagimeno.workouttimer.data.local.entities.TimerEntity

@Database(entities = [TimerEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun timerDao(): TimerDao
}