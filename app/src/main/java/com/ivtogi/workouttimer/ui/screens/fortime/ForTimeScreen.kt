package com.ivtogi.workouttimer.ui.screens.fortime

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.data.Exercise
import com.ivtogi.workouttimer.ui.WorkoutTimerScreen
import com.ivtogi.workouttimer.ui.screens.common.AddExerciseDialog
import com.ivtogi.workouttimer.ui.screens.common.LargeButton
import com.ivtogi.workouttimer.ui.screens.common.TimerFormularySection
import com.ivtogi.workouttimer.ui.screens.common.TopBar

@Composable
fun ForTimeScreen(
    viewModel: ForTimeViewModel = viewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                title = R.string.for_time,
                onBackClick = onBackClick
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                TimerFormularySection(
                    minutes = state.timer.minutes,
                    seconds = state.timer.seconds,
                    onMinutesChange = {
                        viewModel.formatTimerFields(
                            it,
                            UiState.FormatType.MINUTES
                        )
                    },
                    onSecondsChange = {
                        viewModel.formatTimerFields(
                            it,
                            UiState.FormatType.SECONDS
                        )
                    }
                )
                Spacer(modifier = Modifier.height(32.dp))
                ForTimeWorkoutSection(
                    workout = state.workout,
                    onAddExerciseClicked = { viewModel.showDialog() },
                    onDeleteExercise = { viewModel.deleteExercise(it) }
                )
                Spacer(modifier = Modifier.weight(1f))
                LargeButton(
                    textRes = R.string.start,
                    onClick = { TODO() }
                )
                if (state.showDialog) {
                    AddExerciseDialog(
                        addExercise = { quantity, exercise ->
                            viewModel.addExercise(
                                Exercise(quantity, exercise)
                            )
                        },
                        hideDialog = { viewModel.hideDialog() }
                    )
                }
            }

        }
    }
}

@Composable
@Preview(showSystemUi = true)
@Preview(
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,orientation=landscape"
)
fun ForTimeScreenPreview() {
    WorkoutTimerScreen {
        ForTimeScreen(onBackClick = {})
    }
}