package com.ivtogi.workouttimer.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timerId: Int,
    val quantity: Int,
    val name: String
)