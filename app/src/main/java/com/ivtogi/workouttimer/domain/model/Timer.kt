package com.ivtogi.workouttimer.domain.model

data class Timer(
    val id: Int = 0,
    val initial: Int = 0,
    val end: Int = 0,
    val rounds: Int = 0,
    val workout: List<Exercise> = emptyList(),
    val type: Type = Type.FOR_TIME,
    val countDown: CountDown = CountDown.TEN
) {
    enum class Type {
        FOR_TIME, EMOM, AMRAP
    }

    enum class CountDown(val seconds: Int) {
        THREE(3), FIVE(5), TEN(10)
    }
}
