package com.ivtogi.workouttimer.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.ui.WorkTimerApp
import com.ivtogi.workouttimer.ui.screens.common.LargeButton

@Composable
fun HomeScreen(onClick: () -> Unit) {
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
                onClick = onClick
            )
            Spacer(modifier = Modifier.height(16.dp))
            LargeButton(
                textRes = R.string.emom,
                onClick = onClick
            )
            Spacer(modifier = Modifier.height(16.dp))
            LargeButton(
                textRes = R.string.amrap,
                onClick = onClick
            )
        }
        Spacer(modifier = Modifier.padding(1.dp))
    }
}

@Composable
@Preview(showSystemUi = true)
fun HomeScreenPreview() {
    WorkTimerApp {
        HomeScreen({})
    }
}