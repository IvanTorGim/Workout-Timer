package com.ivtogi.workouttimer.ui.screens.home

import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ivtogi.workouttimer.MainActivity
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.ui.screens.common.LargeButton

@Composable
fun HomeScreen(
    onForTimeClick: () -> Unit,
    onEmomClick: () -> Unit,
    onAmrapClick: () -> Unit
) {
    val activity = LocalContext.current as? MainActivity
    LaunchedEffect(Unit) {
        activity?.setOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name).uppercase(),
            style = MaterialTheme.typography.displaySmall
        )
        Column {
            LargeButton(
                textRes = R.string.for_time,
                onClick = onForTimeClick
            )
            LargeButton(
                textRes = R.string.emom,
                onClick = onEmomClick
            )
            LargeButton(
                textRes = R.string.amrap,
                onClick = onAmrapClick
            )
        }
        Spacer(modifier = Modifier.padding(1.dp))
    }
}