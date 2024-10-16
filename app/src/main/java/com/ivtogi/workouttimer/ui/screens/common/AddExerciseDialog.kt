package com.ivtogi.workouttimer.ui.screens.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.core.Constants.Companion.LIMIT_QUANTITY_REPS_EXERCISE
import com.ivtogi.workouttimer.core.formatNumberField

@Composable
fun AddExerciseDialog(addExercise: (String, String) -> Unit, hideDialog: () -> Unit) {
    Dialog(onDismissRequest = { hideDialog() }) {
        var exercise by remember { mutableStateOf("") }
        var quantity by remember { mutableStateOf("") }
        Card {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = stringResource(id = R.string.add_exercise))
                OutlinedTextField(
                    value = quantity,
                    onValueChange = {
                        quantity = it.formatNumberField(LIMIT_QUANTITY_REPS_EXERCISE)
                    },
                    label = { Text(text = stringResource(id = R.string.quantity)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Done
                    ),
                    placeholder = { Text(text = "0") }
                )
                OutlinedTextField(
                    value = exercise,
                    onValueChange = { if (exercise.length < 40) exercise = it },
                    label = { Text(text = stringResource(id = R.string.exercise)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = hideDialog) {
                        Text(
                            text = stringResource(id = R.string.cancel),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = { addExercise(quantity, exercise) }) {
                        Text(text = stringResource(id = R.string.add))
                    }
                }
            }
        }
    }
}