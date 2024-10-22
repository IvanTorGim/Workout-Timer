package com.itortosagimeno.workouttimer.domain.model

data class Timer(
    val id: Int = 0,
    val initial: Int = 0,
    val end: Int = 0,
    val rounds: Int = 1,
    val workout: String = "",
    val type: Type = Type.FOR_TIME,
    val countdown: Int = CountDown.TEN.seconds
) {
    enum class Type(val value: Int) {
        FOR_TIME(1), EMOM(2), AMRAP(3)
    }

    enum class CountDown(val seconds: Int) {
        THREE(3), FIVE(5), TEN(10)
    }
}
