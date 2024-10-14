package com.ivtogi.workouttimer.data.mappers

import com.ivtogi.workouttimer.data.local.entities.TimerEntity
import com.ivtogi.workouttimer.domain.model.Exercise
import com.ivtogi.workouttimer.domain.model.Timer

fun TimerEntity.toDomain(exercises: List<Exercise>) = Timer(
    id = id,
    initial = this.initial,
    end = end,
    rounds = rounds,
    workout = exercises,
    type = type
)

fun Timer.toEntity() = TimerEntity(
    initial = initial,
    end = end,
    rounds = rounds,
    type = type
)