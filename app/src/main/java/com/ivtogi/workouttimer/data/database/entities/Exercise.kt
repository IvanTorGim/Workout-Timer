package com.ivtogi.workouttimer.data.database.entities

import androidx.room.Entity

@Entity
data class Exercise(
    val timerId: Int,
    val quantity: Int,
    val name: String
)