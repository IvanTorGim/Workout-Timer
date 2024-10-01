package com.ivtogi.workouttimer.ui.screens.fortime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.ui.WorkTimerApp
import com.ivtogi.workouttimer.ui.screens.common.LargeButton

@Composable
fun ForTimeScreen() {
    var hours by remember { mutableStateOf("") }
    var minutes by remember { mutableStateOf("") }
    var seconds by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp, 0.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = hours,
                onValueChange = { hours = formatNumbers(it, 99) },
                suffix = { Text(text = "h") },
                placeholder = { Text(text = "0") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = minutes,
                onValueChange = { minutes = formatNumbers(it, 60) },
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
                onValueChange = { seconds = formatNumbers(it, 60) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Done
                ),
                suffix = { Text(text = "s") },
                placeholder = { Text(text = "0") },
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        LargeButton(
            textRes = R.string.start,
            onClick = { TODO() }
        )
    }
}

private fun formatNumbers(number: String, limit: Int): String {
    try {
        val num = number.toInt()
        return if (num > limit) limit.toString()
        else if (num < 0) ""
        else num.toString()
    } catch (e: Exception) {
        return ""
    }
}

@Composable
@Preview(showSystemUi = true)
fun ForTimeScreenPreview() {
    WorkTimerApp {
        ForTimeScreen()
    }
}