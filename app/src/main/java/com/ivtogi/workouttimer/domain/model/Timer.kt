package com.ivtogi.workouttimer.domain.model

data class Timer(
    val id: Int = 0,
    val initial: Int,
    val end: Int,
    val rounds: Int,
    val workout: List<Exercise>
)
