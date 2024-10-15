package com.ivtogi.workouttimer.ui.screens.timer

import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.core.toStringMinutes
import com.ivtogi.workouttimer.core.toStringSeconds
import com.ivtogi.workouttimer.domain.model.Timer
import com.ivtogi.workouttimer.ui.screens.common.TopBar
import com.ivtogi.workouttimer.ui.screens.timer.composable.MarqueeText
import com.ivtogi.workouttimer.ui.screens.timer.composable.TimerIconButton

@Composable
fun TimerScreen(
    viewModel: TimerScreenViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val localConfiguration = LocalConfiguration.current
    Scaffold(
        topBar = {
            val string = when (state.timer.type) {
                Timer.Type.FOR_TIME -> R.string.for_time
                Timer.Type.EMOM -> R.string.emom
                Timer.Type.AMRAP -> R.string.amrap
            }
            val timer = when (state.timer.type) {
                Timer.Type.FOR_TIME -> state.timer.end
                else -> state.timer.initial
            }
            TopBar(
                title = string,
                secondaryText = "${timer.toStringMinutes()}:${timer.toStringSeconds()}",
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            if (localConfiguration.screenWidthDp > 600) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 0.dp)
                    ) {

                        Text(
                            text = "${state.actualTime.toStringMinutes()}:${state.actualTime.toStringSeconds()}",
                            style = MaterialTheme.typography.displayLarge,
                            modifier = Modifier.align(Alignment.Center)
                        )
                        if (state.isPaused) {
                            TimerIconButton(
                                icon = Icons.Default.PlayArrow,
                                contentDescription = R.string.play_timer,
                                onClick = { viewModel.startTimer() },
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                            if (state.isStarted) {
                                TimerIconButton(
                                    icon = Icons.Default.Replay,
                                    contentDescription = R.string.reset_timer,
                                    onClick = { viewModel.resetTimer() },
                                    modifier = Modifier.align(Alignment.CenterStart)
                                )
                            }
                        } else {
                            TimerIconButton(
                                icon = Icons.Default.Pause,
                                contentDescription = R.string.pause_timer,
                                onClick = { viewModel.pauseTimer() },
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    }
                    MarqueeText(workout = state.timer.workout, isPaused = state.isPaused)
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column {
                        Text(
                            text = state.actualTime.toStringMinutes(),
                            style = MaterialTheme.typography.displayLarge
                        )
                        Text(
                            text = state.actualTime.toStringSeconds(),
                            style = MaterialTheme.typography.displayLarge
                        )

                    }
                    MarqueeText(workout = state.timer.workout, isPaused = state.isPaused)
                    if (state.isPaused) {
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            TimerIconButton(
                                icon = Icons.Default.PlayArrow,
                                contentDescription = R.string.play_timer,
                                onClick = { viewModel.startTimer() },
                            )
                            if (state.isStarted) {
                                TimerIconButton(
                                    icon = Icons.Default.Replay,
                                    contentDescription = R.string.reset_timer,
                                    onClick = { viewModel.resetTimer() },
                                )
                            }
                        }
                    } else {
                        TimerIconButton(
                            icon = Icons.Default.Pause,
                            contentDescription = R.string.pause_timer,
                            onClick = { viewModel.pauseTimer() },
                        )
                    }
                }
            }
        }
    }
}