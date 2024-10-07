package com.ivtogi.workouttimer.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TimerWithExercises(
    @Embedded val timer: Timer,
    @Relation(parentColumn = "id", entityColumn = "timerId")
    val exercises: List<Exercise>
)