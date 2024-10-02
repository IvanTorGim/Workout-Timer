package com.ivtogi.workouttimer.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ivtogi.workouttimer.ui.screens.navigation.Navigation
import com.ivtogi.workouttimer.ui.theme.WorkoutTimerTheme

@Composable
fun WorkTimerApp() {
    val navHostController = rememberNavController()
    WorkoutTimerScreen {
        Box(modifier = Modifier.fillMaxSize()) {
            Navigation(navHostController = navHostController)
        }
    }
}


@Composable
fun WorkoutTimerScreen(content: @Composable () -> Unit) {
    WorkoutTimerTheme {
        Surface {
            content()
        }
    }
}