package com.ivtogi.workouttimer.ui.screens.emom

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
import com.ivtogi.workouttimer.MainActivity
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.core.Constants.Companion.LIMIT_EMOM_MINUTES
import com.ivtogi.workouttimer.core.Constants.Companion.ONE_MIN_IN_SEC
import com.ivtogi.workouttimer.core.Constants.Companion.STEP_INTERVAL_EMOM
import com.ivtogi.workouttimer.ui.screens.common.CountdownSelector
import com.ivtogi.workouttimer.ui.screens.common.LargeButton
import com.ivtogi.workouttimer.ui.screens.common.RoundsSection
import com.ivtogi.workouttimer.ui.screens.common.TimerSection
import com.ivtogi.workouttimer.ui.screens.common.TopBar
import com.ivtogi.workouttimer.ui.screens.common.WorkoutSection

@Composable
fun EmomScreen(
    viewModel: EmomViewModel = hiltViewModel(),
    navigateToTimer: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val activity = LocalContext.current as? MainActivity

    LaunchedEffect(Unit) {
        activity?.setOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    Scaffold(
        topBar = {
            TopBar(
                title = R.string.emom,
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
                text = stringResource(id = R.string.emom_description),
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
                title = R.string.every,
                time = state.time,
                stepInterval = STEP_INTERVAL_EMOM,
                endTime = LIMIT_EMOM_MINUTES * ONE_MIN_IN_SEC,
                onTimeChanged = { viewModel.onTimeChanged(it) },
            )
            Spacer(modifier = Modifier.height(16.dp))
            RoundsSection(
                rounds = state.rounds,
                onRoundsChanged = { viewModel.onRoundsChanged(it) }
            )
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