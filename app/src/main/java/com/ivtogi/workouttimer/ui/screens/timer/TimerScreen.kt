package com.ivtogi.workouttimer.ui.screens.timer

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.core.toStringMinutes
import com.ivtogi.workouttimer.core.toStringSeconds
import com.ivtogi.workouttimer.ui.screens.common.TopBar

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
                    Text(
                        text = "${state.actualTime.toStringMinutes()}:${state.actualTime.toStringSeconds()}",
                        style = MaterialTheme.typography.displayLarge
                    )
                    Text(
                        text = state.timer.workout.joinToString("  ") { it.toFormatString() },
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier.basicMarquee(velocity = 100.dp)
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
                        modifier = Modifier.basicMarquee(velocity = 100.dp)
                    )
                    if (state.isStarted) {
                        IconButton(
                            onClick = { viewModel.pauseTimer() },
                            modifier = Modifier.size(80.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Pause,
                                contentDescription = stringResource(id = R.string.pause_timer),
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    } else {
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            IconButton(
                                onClick = { viewModel.startTimer() },
                                modifier = Modifier.size(80.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = stringResource(id = R.string.play_timer),
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            if (state.actualTime != state.timer.initial) {
                                IconButton(
                                    onClick = { viewModel.resetTimer() },
                                    modifier = Modifier.size(80.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Replay,
                                        contentDescription = stringResource(id = R.string.reset_timer),
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}