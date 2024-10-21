package com.itortosagimeno.workouttimer.ui.screens.timer.composable

import androidx.compose.foundation.basicMarquee
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MarqueeText(workout: String, isPaused: Boolean) {
    Text(
        text = workout,
        style = MaterialTheme.typography.displayMedium,
        modifier = Modifier.basicMarquee(
            iterations = Int.MAX_VALUE,
            repeatDelayMillis = 0,
            velocity = if (isPaused) 0.dp else 100.dp
        )
    )
}