package com.itortosagimeno.workouttimer.ui.screens.select_timer

import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.itortosagimeno.workouttimer.MainActivity
import com.itortosagimeno.workouttimer.R
import com.itortosagimeno.workouttimer.core.Constants.Companion.LIMIT_EMOM_SECONDS
import com.itortosagimeno.workouttimer.core.Constants.Companion.LIMIT_FOR_TIME_AMRAP_SECONDS
import com.itortosagimeno.workouttimer.core.Constants.Companion.STEP_INTERVAL_EMOM
import com.itortosagimeno.workouttimer.core.Constants.Companion.STEP_INTERVAL_FOR_TIME_AMRAP
import com.itortosagimeno.workouttimer.domain.model.Timer.Type.AMRAP
import com.itortosagimeno.workouttimer.domain.model.Timer.Type.EMOM
import com.itortosagimeno.workouttimer.domain.model.Timer.Type.FOR_TIME
import com.itortosagimeno.workouttimer.ui.screens.common.CountdownSelector
import com.itortosagimeno.workouttimer.ui.screens.common.LargeButton
import com.itortosagimeno.workouttimer.ui.screens.common.RoundsSection
import com.itortosagimeno.workouttimer.ui.screens.common.TimerSection
import com.itortosagimeno.workouttimer.ui.screens.common.TopBar
import com.itortosagimeno.workouttimer.ui.screens.common.WorkoutSection

@Composable
fun SelectTimerScreen(
    viewModel: SelectTimerViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    navigateToTimer: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val activity = LocalContext.current as? MainActivity

    LaunchedEffect(Unit) {
        activity?.setOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    Scaffold(
        topBar = {
            TopBar(
                title = when (state.type) {
                    FOR_TIME -> R.string.for_time
                    EMOM -> R.string.emom
                    AMRAP -> R.string.amrap
                },
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(32.dp, 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(
                    id = when (state.type) {
                        FOR_TIME -> R.string.for_time_description
                        EMOM -> R.string.emom_description
                        AMRAP -> R.string.amrap_description
                    }
                ),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            CountdownSelector(
                seconds = state.countdown,
                onSelected = { viewModel.onCountdownSelected(it) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TimerSection(
                title = when (state.type) {
                    FOR_TIME, AMRAP -> R.string.timer
                    EMOM -> R.string.every
                },
                time = state.time,
                stepInterval = when (state.type) {
                    FOR_TIME, AMRAP -> STEP_INTERVAL_FOR_TIME_AMRAP
                    EMOM -> STEP_INTERVAL_EMOM
                },
                endTime = when (state.type) {
                    FOR_TIME, AMRAP -> LIMIT_FOR_TIME_AMRAP_SECONDS
                    EMOM -> LIMIT_EMOM_SECONDS
                },
                onTimeChanged = { viewModel.onTimeChanged(it) },
            )
            if (state.type == EMOM) {
                Spacer(modifier = Modifier.height(16.dp))
                RoundsSection(
                    rounds = state.rounds,
                    onRoundsChanged = { viewModel.onRoundsChanged(it) }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            WorkoutSection(
                workout = state.workout,
                onWorkoutChanged = { viewModel.onWorkoutChanged(it) }
            )
            Spacer(modifier = Modifier.weight(1f))
            LargeButton(
                textRes = R.string.start,
                onClick = {
                    viewModel.saveTimer(navigateToTimer)
                }
            )
        }
    }
}