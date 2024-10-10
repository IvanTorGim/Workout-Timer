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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.domain.model.Exercise
import com.ivtogi.workouttimer.ui.screens.common.AddExerciseDialog
import com.ivtogi.workouttimer.ui.screens.common.LargeButton
import com.ivtogi.workouttimer.ui.screens.common.TimerFormularySection
import com.ivtogi.workouttimer.ui.screens.common.TopBar
import com.ivtogi.workouttimer.ui.screens.fortime.composable.ForTimeWorkoutSection

@Composable
fun ForTimeScreen(
    viewModel: ForTimeViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    navigateToTimer: () -> Unit
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
                    .padding(32.dp, 8.dp)
            ) {
                TimerFormularySection(
                    minutes = state.minutes,
                    seconds = state.seconds,
                    onMinutesChange = { viewModel.onMinutesChanged(it) },
                    onSecondsChange = { viewModel.onSecondsChanged(it) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                ForTimeWorkoutSection(
                    workout = state.workout,
                    onAddExerciseClicked = { viewModel.showDialog() },
                    onDeleteExercise = { viewModel.deleteExercise(it) },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                LargeButton(
                    textRes = R.string.start,
                    onClick = {
                        viewModel.saveTimer()
                        navigateToTimer()
                    }
                )
                if (state.showDialog) {
                    AddExerciseDialog(
                        addExercise = { quantity, exercise ->
                            viewModel.addExercise(
                                Exercise(
                                    quantity = quantity,
                                    name = exercise
                                )
                            )
                        },
                        hideDialog = { viewModel.hideDialog() }
                    )
                }
            }

        }
    }
}