package com.ivtogi.workouttimer.data

data class Exercise(
    val quantity: String = "",
    val name: String = ""
) {
    fun toFormatString(): String = "-$quantity $name"
}
