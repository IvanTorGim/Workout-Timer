package com.ivtogi.workouttimer.ui

fun String.formatNumberField(limit: Int): String {
    var result = ""
    this.toIntOrNull()?.let {
        result = if (it > limit) limit.toString()
        else if (it < 0) ""
        else it.toString()
    }
    return result
}