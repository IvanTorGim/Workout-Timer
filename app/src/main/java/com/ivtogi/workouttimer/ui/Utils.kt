package com.ivtogi.workouttimer.ui

import java.util.Locale

fun String.formatNumberField(limit: Int): String {
    var result = ""
    this.toIntOrNull()?.let {
        result = if (it > limit) limit.toString()
        else if (it < 0) ""
        else it.toString()
    }
    return result
}

fun Int.toMinutes() = String.format(Locale.US, "%02d", this / 60)

fun Int.toSeconds() = String.format(Locale.US, "%02d", this % 60)