package com.ivtogi.workouttimer.ui.screens.timer

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.ui.WorkoutTimerScreen
import com.ivtogi.workouttimer.ui.screens.common.TopBar
import com.ivtogi.workouttimer.ui.toMinutes
import com.ivtogi.workouttimer.ui.toSeconds

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
                secondaryText = "${state.timer.initial.toMinutes()}:${state.timer.initial.toSeconds()}",
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

            if (localConfiguration.screenWidthDp > 600) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "${state.timer.initial.toMinutes()}:${state.timer.initial.toSeconds()}",
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
                            text = state.timer.initial.toMinutes(),
                            style = MaterialTheme.typography.displayLarge
                        )
                        Text(
                            text = state.timer.initial.toSeconds(),
                            style = MaterialTheme.typography.displayLarge
                        )

                    }
                    Text(
                        text = state.timer.workout.joinToString("  ") { it.toFormatString() },
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier.basicMarquee(velocity = 100.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
@Preview(device = "spec:parent=pixel_5,orientation=landscape")
fun TimerScreenPreview() {
    WorkoutTimerScreen {
        TimerScreen() {}
    }
}