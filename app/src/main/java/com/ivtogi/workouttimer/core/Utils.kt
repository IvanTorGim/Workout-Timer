package com.ivtogi.workouttimer.core

import com.ivtogi.workouttimer.core.Constants.Companion.ONE_MIN_IN_SEC
import java.util.Locale

fun String.formatNumberField(start: Int, end: Int): String {
    var result = ""
    this.toIntOrNull()?.let {
        result = if (it > end) end.toString()
        else if (it < start) ""
        else it.toString()
    }
    return result
}

fun String.toIntMinutes(): Int = if (this.isBlank()) 0 else this.toInt() * ONE_MIN_IN_SEC

fun String.toIntSeconds(): Int = if (this.isBlank()) 0 else this.toInt()

fun String.toIntRounds(): Int = if (this.isBlank()) 1 else this.toInt()

fun Int.toStringMinutes() = String.format(Locale.US, "%02d", this / ONE_MIN_IN_SEC)

fun Int.toStringSeconds() = String.format(Locale.US, "%02d", this % ONE_MIN_IN_SEC)
