package com.itortosagimeno.workouttimer.ui.screens.home

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
import com.itortosagimeno.workouttimer.MainActivity
import com.itortosagimeno.workouttimer.R
import com.itortosagimeno.workouttimer.domain.model.Timer
import com.itortosagimeno.workouttimer.ui.screens.common.LargeButton

@Composable
fun HomeScreen(onButtonClick: (Int) -> Unit) {
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
                onClick = { onButtonClick(Timer.Type.FOR_TIME.value) }
            )
            LargeButton(
                textRes = R.string.emom,
                onClick = { onButtonClick(Timer.Type.EMOM.value) }
            )
            LargeButton(
                textRes = R.string.amrap,
                onClick = { onButtonClick(Timer.Type.AMRAP.value) }
            )
        }
        Spacer(modifier = Modifier.padding(1.dp))
    }
}