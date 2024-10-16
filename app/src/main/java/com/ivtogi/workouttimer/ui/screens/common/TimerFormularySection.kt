package com.ivtogi.workouttimer.ui.screens.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun TimerFormularySection(
    @StringRes title: Int,
    minutes: String,
    seconds: String,
    onMinutesChange: (String) -> Unit,
    onSecondsChange: (String) -> Unit
) {
    Text(
        text = stringResource(id = title),
        style = MaterialTheme.typography.titleLarge
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = minutes,
            onValueChange = onMinutesChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            ),
            suffix = { Text(text = "m") },
            placeholder = { Text(text = "0") },
            modifier = Modifier.weight(1f)
        )
        OutlinedTextField(
            value = seconds,
            onValueChange = onSecondsChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            ),
            suffix = { Text(text = "s") },
            placeholder = { Text(text = "0") },
            modifier = Modifier.weight(1f)
        )
    }
}