package com.ivtogi.workouttimer.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Timers")
data class TimerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val initial: Int = 0,
    val end: Int = 0,
    val rounds: Int = 0
)