package com.ivtogi.workouttimer.data.mappers

import com.ivtogi.workouttimer.data.local.entities.ExerciseEntity
import com.ivtogi.workouttimer.domain.model.Exercise

fun ExerciseEntity.toDomain() = Exercise(
    id = id,
    quantity = quantity.toString(),
    name = name
)

fun Exercise.toEntity() = ExerciseEntity(
    quantity = quantity.toInt(),
    name = name
)