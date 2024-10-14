package com.ivtogi.workouttimer.domain.model

data class Timer(
    val id: Int = 0,
    val initial: Int = 0,
    val end: Int = 0,
    val rounds: Int = 0,
    val workout: List<Exercise> = emptyList(),
    val type: Type = Type.FOR_TIME
) {
    enum class Type {
        FOR_TIME, EMOM, AMRAP
    }
}
