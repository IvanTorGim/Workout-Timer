package com.ivtogi.workouttimer.ui.screens.common

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
import com.ivtogi.workouttimer.R

@Composable
fun RoundsSection(
    rounds: String,
    onRoundsChanged: (String) -> Unit
) {
    Text(
        text = stringResource(id = R.string.rounds),
        style = MaterialTheme.typography.titleLarge
    )
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = rounds,
        onValueChange = onRoundsChanged,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        ),
        placeholder = { Text(text = "1") },
        modifier = Modifier.fillMaxWidth()
    )
}