package com.ivtogi.workouttimer.ui.screens.timer

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
import com.ivtogi.workouttimer.ui.screens.common.TopBar
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
            TopBar(
                title = R.string.for_time,
                secondaryText = "${state.timer.end.toStringMinutes()}:${state.timer.end.toStringSeconds()}",
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
                        if (state.isStarted) {
                            TimerIconButton(
                                icon = Icons.Default.Pause,
                                contentDescription = R.string.pause_timer,
                                onClick = { viewModel.pauseTimer() },
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        } else {
                            TimerIconButton(
                                icon = Icons.Default.PlayArrow,
                                contentDescription = R.string.play_timer,
                                onClick = { viewModel.startTimer() },
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                            if (state.actualTime != state.timer.initial) {
                                TimerIconButton(
                                    icon = Icons.Default.Replay,
                                    contentDescription = R.string.reset_timer,
                                    onClick = { viewModel.resetTimer() },
                                    modifier = Modifier.align(Alignment.CenterStart)
                                )
                            }
                        }
                    }
                    Text(
                        text = state.timer.workout.joinToString("  ") { it.toFormatString() },
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier.basicMarquee(velocity = if (state.isStarted) 100.dp else 0.dp)
                    )
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
                    Text(
                        text = state.timer.workout.joinToString("  ") { it.toFormatString() },
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier.basicMarquee(velocity = if (state.isStarted) 100.dp else 0.dp)
                    )
                    if (state.isStarted) {
                        TimerIconButton(
                            icon = Icons.Default.Pause,
                            contentDescription = R.string.pause_timer,
                            onClick = { viewModel.pauseTimer() },
                        )
                    } else {
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            TimerIconButton(
                                icon = Icons.Default.PlayArrow,
                                contentDescription = R.string.play_timer,
                                onClick = { viewModel.startTimer() },
                            )
                            if (state.actualTime != state.timer.initial) {
                                TimerIconButton(
                                    icon = Icons.Default.Replay,
                                    contentDescription = R.string.reset_timer,
                                    onClick = { viewModel.resetTimer() },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}