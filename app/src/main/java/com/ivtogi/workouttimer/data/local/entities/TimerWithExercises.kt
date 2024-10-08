package com.ivtogi.workouttimer.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TimerWithExercises(
    @Embedded val timer: TimerEntity = TimerEntity(),
    @Relation(parentColumn = "id", entityColumn = "timerId")
    val exercises: List<ExerciseEntity> = emptyList()
)