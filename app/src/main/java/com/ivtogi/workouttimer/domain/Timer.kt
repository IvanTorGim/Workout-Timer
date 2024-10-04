package com.ivtogi.workouttimer.domain

data class Timer(
    val initial: Int,
    val end: Int,
    val rounds: Int,
    val workout: List<Exercise>
)
