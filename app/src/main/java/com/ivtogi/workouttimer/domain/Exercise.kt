package com.ivtogi.workouttimer.domain

data class Exercise(
    val quantity: String = "",
    val name: String = ""
) {
    fun toFormatString(): String = "-$quantity $name"
}
