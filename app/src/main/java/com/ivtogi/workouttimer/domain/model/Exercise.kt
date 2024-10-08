package com.ivtogi.workouttimer.domain.model

data class Exercise(
    val id: Int = 0,
    val quantity: String = "",
    val name: String = ""
) {
    fun toFormatString(): String = "-$quantity $name"
}
