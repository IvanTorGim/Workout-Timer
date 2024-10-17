package com.ivtogi.workouttimer.ui.screens.common

import androidx.compose.foundation.layout.Column
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
fun WorkoutSection(
    workout: String,
    onWorkoutChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = R.string.workout),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = workout,
            onValueChange = onWorkoutChanged,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            placeholder = { Text(text = "description") },
            minLines = 5,
            maxLines = 5,
            modifier = Modifier.fillMaxWidth()
        )
    }
}