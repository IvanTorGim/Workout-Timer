package com.ivtogi.workouttimer.core

import com.ivtogi.workouttimer.core.Constants.Companion.ONE_MIN_IN_SEC
import java.util.Locale

fun String.toIntTime(): Int {
    val timeSplit = this.split(":")
    val minutes = timeSplit[0].toInt() * ONE_MIN_IN_SEC
    val seconds = timeSplit[1].toInt()
    return minutes + seconds
}

fun Int.toStringTime(): String {
    val minutes = String.format(Locale.US, "%02d", this / ONE_MIN_IN_SEC)
    val seconds = String.format(Locale.US, "%02d", this % ONE_MIN_IN_SEC)
    return "$minutes:$seconds"
}