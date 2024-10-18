package com.ivtogi.workouttimer.ui.screens.fortime

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
import com.ivtogi.workouttimer.ui.screens.common.CountdownSelector
import com.ivtogi.workouttimer.ui.screens.common.LargeButton
import com.ivtogi.workouttimer.ui.screens.common.TimerFormularySection
import com.ivtogi.workouttimer.ui.screens.common.TopBar
import com.ivtogi.workouttimer.ui.screens.common.WorkoutSection

@Composable
fun ForTimeScreen(
    viewModel: ForTimeViewModel = hiltViewModel(),
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
                title = R.string.for_time,
                onBackClick = onBackClick
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(32.dp, 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.for_time_description),
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
            TimerFormularySection(
                title = R.string.timer,
                minutes = state.minutes,
                seconds = state.seconds,
                onMinutesChange = { viewModel.onMinutesChanged(it) },
                onSecondsChange = { viewModel.onSecondsChanged(it) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            WorkoutSection(
                workout = state.workout,
                onWorkoutChanged = { viewModel.onWorkoutChanged(it) }
            )
            Spacer(modifier = Modifier.weight(1f))
            LargeButton(
                textRes = R.string.start,
                onClick = { viewModel.saveTimer(navigateToTimer) }
            )
        }
    }
}