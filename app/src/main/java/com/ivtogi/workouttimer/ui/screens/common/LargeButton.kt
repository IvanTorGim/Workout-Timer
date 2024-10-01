package com.ivtogi.workouttimer.ui.screens.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun LargeButton(@StringRes textRes: Int, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = { onClick() }, modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp, 8.dp)
    ) {
        Text(
            stringResource(id = textRes),
            style = MaterialTheme.typography.titleMedium
        )
    }
}