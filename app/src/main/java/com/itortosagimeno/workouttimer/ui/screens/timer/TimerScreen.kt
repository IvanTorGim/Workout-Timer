package com.itortosagimeno.workouttimer.ui.screens.timer

import android.content.pm.ActivityInfo
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.itortosagimeno.workouttimer.MainActivity
import com.itortosagimeno.workouttimer.R
import com.itortosagimeno.workouttimer.core.toStringTime
import com.itortosagimeno.workouttimer.domain.model.Timer.Type.AMRAP
import com.itortosagimeno.workouttimer.domain.model.Timer.Type.EMOM
import com.itortosagimeno.workouttimer.domain.model.Timer.Type.FOR_TIME
import com.itortosagimeno.workouttimer.ui.screens.timer.composable.MarqueeText
import com.itortosagimeno.workouttimer.ui.screens.timer.composable.TimerTopBar

@Composable
fun TimerScreen(
    viewModel: TimerScreenViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val localConfiguration = LocalConfiguration.current
    val activity = LocalContext.current as? MainActivity

    DisposableEffect(Unit) {
        activity?.apply {
            setOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
            window.addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
        onDispose {
            activity?.window?.clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    Scaffold(
        topBar = {
            val string = when (state.timer.type) {
                FOR_TIME -> R.string.for_time
                EMOM -> R.string.emom
                AMRAP -> R.string.amrap
            }
            val timer = when (state.timer.type) {
                FOR_TIME -> state.timer.end
                else -> state.timer.initial
            }
            TimerTopBar(
                title = string,
                timer = timer.toStringTime(),
                rounds = "${state.actualRound}/${state.timer.rounds}",
                isPaused = state.isPaused,
                isStarted = state.isStarted,
                onBackClick = onBackClick,
                onPlayClick = { viewModel.startTimer() },
                onPauseClick = { viewModel.pauseTimer() },
                onResetClick = { viewModel.resetTimer() }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            val progress = when (state.timer.type) {
                FOR_TIME -> state.actualTime.toFloat() / state.timer.end.toFloat()
                EMOM, AMRAP -> (state.timer.initial.toFloat() - state.actualTime.toFloat()) / state.timer.initial.toFloat()
            }
            val animatedProgress by animateFloatAsState(
                targetValue = if (state.isCountdown) 0f else progress,
                animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
                label = "Time bar"
            )
            if (localConfiguration.screenWidthDp > 600) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    LinearProgressIndicator(
                        progress = { animatedProgress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )
                    Text(
                        text = state.actualTime.toStringTime(),
                        style = MaterialTheme.typography.displayLarge
                    )
                    MarqueeText(workout = state.timer.workout, isPaused = state.isPaused)
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    LinearProgressIndicator(
                        progress = { animatedProgress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )
                    Column {
                        val minutes = state.actualTime.toStringTime().split(":")[0]
                        val seconds = state.actualTime.toStringTime().split(":")[1]
                        Text(
                            text = minutes,
                            style = MaterialTheme.typography.displayLarge
                        )
                        Text(
                            text = seconds,
                            style = MaterialTheme.typography.displayLarge
                        )
                    }
                    MarqueeText(workout = state.timer.workout, isPaused = state.isPaused)
                }
            }
        }
    }
}