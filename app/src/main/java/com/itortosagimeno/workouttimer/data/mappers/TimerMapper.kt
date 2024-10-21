package com.itortosagimeno.workouttimer.data.mappers

import com.itortosagimeno.workouttimer.data.local.entities.TimerEntity
import com.itortosagimeno.workouttimer.domain.model.Timer

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