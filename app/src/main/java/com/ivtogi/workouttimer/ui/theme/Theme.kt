package com.ivtogi.workouttimer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Orange55,
    onPrimary = White,
    secondaryContainer = Orange80,
    onSecondaryContainer = Black
)

private val LightColorScheme = lightColorScheme(
    primary = Orange55,
    onPrimary = White,
    secondaryContainer = Orange80,
    onSecondaryContainer = Black
)

@Composable
fun WorkoutTimerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}