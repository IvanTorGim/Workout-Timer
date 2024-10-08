package com.ivtogi.workouttimer.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timerId: Int = 0,
    val quantity: Int = 0,
    val name: String = ""
)