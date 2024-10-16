package com.ivtogi.workouttimer.data.mappers

import com.ivtogi.workouttimer.data.local.entities.TimerEntity
import com.ivtogi.workouttimer.domain.model.Timer

fun TimerEntity.toDomain() = Timer(
    id = id,
    initial = this.initial,
    end = end,
    rounds = rounds,
    workout = workout,
    type = type,
    countdown = countdown
)

fun Timer.toEntity() = TimerEntity(
    initial = initial,
    end = end,
    rounds = rounds,
    workout = workout,
    type = type,
    countdown = countdown
)