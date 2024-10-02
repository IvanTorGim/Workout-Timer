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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.ui.WorkoutTimerScreen
import com.ivtogi.workouttimer.ui.screens.common.TopBar

@Composable
fun TimerScreen(
    viewModel: TimerScreenViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    val localConfiguration = LocalConfiguration.current

    Scaffold(
        topBar = { TopBar(title = R.string.for_time, onBackClick = {}) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (localConfiguration.screenWidthDp > 600) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "${state.timer.minutes}:${state.timer.seconds}",
                        style = MaterialTheme.typography.displayLarge
                    )
                    Text(
                        text = state.workout.joinToString("  ") { it.toFormatString() },
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
                            text = state.timer.minutes,
                            style = MaterialTheme.typography.displayLarge
                        )
                        Text(
                            text = state.timer.seconds,
                            style = MaterialTheme.typography.displayLarge
                        )

                    }
                    Text(
                        text = state.workout.joinToString("  ") { it.toFormatString() },
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
        TimerScreen()
    }
}