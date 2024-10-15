package com.ivtogi.workouttimer.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ivtogi.workouttimer.domain.model.Timer

@Entity(tableName = "timers")
data class TimerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val initial: Int = 0,
    val end: Int = 0,
    val rounds: Int = 0,
    val finished: Boolean = false,
    val type: Timer.Type = Timer.Type.FOR_TIME,
    val countdown: Int = Timer.CountDown.TEN.seconds
)