package com.ivtogi.workouttimer.ui.screens.timer.composable

import androidx.compose.foundation.basicMarquee
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ivtogi.workouttimer.domain.model.Exercise

@Composable
fun MarqueeText(workout: List<Exercise>, isPaused: Boolean) {
    Text(
        text = workout.joinToString("  ") { it.toFormatString() },
        style = MaterialTheme.typography.displayMedium,
        modifier = Modifier.basicMarquee(
            iterations = Int.MAX_VALUE,
            repeatDelayMillis = 0,
            velocity = if (isPaused) 0.dp else 100.dp
        )
    )
}