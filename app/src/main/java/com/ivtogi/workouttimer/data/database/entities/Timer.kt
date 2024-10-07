package com.ivtogi.workouttimer.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Timers")
data class Timer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val initial: Int,
    val end: Int,
    val rounds: Int
)