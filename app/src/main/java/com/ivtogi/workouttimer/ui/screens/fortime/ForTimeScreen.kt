package com.ivtogi.workouttimer.ui.screens.fortime

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.ui.WorkTimerApp
import com.ivtogi.workouttimer.ui.screens.common.AddExerciseDialog
import com.ivtogi.workouttimer.ui.screens.common.LargeButton
import com.ivtogi.workouttimer.ui.screens.common.TimerFormularySection

@Composable
fun ForTimeScreen(
    viewModel: ForTimeViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        TimerFormularySection(
            hours = state.hours,
            minutes = state.minutes,
            seconds = state.seconds,
            onHoursChange = { viewModel.formatTimerFields(it, UiState.FormatType.HOURS) },
            onMinutesChange = { viewModel.formatTimerFields(it, UiState.FormatType.MINUTES) },
            onSecondsChange = { viewModel.formatTimerFields(it, UiState.FormatType.SECONDS) }
        )
        Spacer(modifier = Modifier.height(32.dp))
        ForTimeWorkoutSection(
            workout = state.workout,
            onAddExerciseClicked = { viewModel.showDialog() })
        Spacer(modifier = Modifier.weight(1f))
        LargeButton(
            textRes = R.string.start,
            onClick = { TODO() }
        )
        if (state.showDialog) {
            AddExerciseDialog(
                addExercise = { quantity, exercise -> viewModel.addExercise(quantity, exercise) },
                hideDialog = { viewModel.hideDialog() }
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun ForTimeScreenPreview() {
    WorkTimerApp {
        ForTimeScreen()
    }
}