package com.ivtogi.workouttimer.ui.screens.fortime

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.data.Exercise
import com.ivtogi.workouttimer.ui.screens.common.ExerciseItem

@Composable
fun ForTimeWorkoutSection(
    workout: List<Exercise>,
    onAddExerciseClicked: () -> Unit,
    onDeleteExercise: (Int) -> Unit
) {
    Text(
        text = stringResource(id = R.string.workout),
        style = MaterialTheme.typography.titleLarge
    )
    Spacer(modifier = Modifier.height(8.dp))
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(items = workout) { index, exercise ->
            ExerciseItem(
                exercise = exercise,
                index = index,
                onDelete = { onDeleteExercise(index) }
            )
        }
        item {
            TextButton(
                onClick = onAddExerciseClicked,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_exercise)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(id = R.string.add_exercise))
            }
        }
    }
}