package com.ivtogi.workouttimer.ui.screens.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.data.Exercise
import com.ivtogi.workouttimer.ui.WorkoutTimerScreen

@Composable
fun ExerciseItem(
    exercise: Exercise,
    index: Int,
    onDelete: (Int) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = exercise.toFormatString(),
            style = MaterialTheme.typography.headlineSmall,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { onDelete(index) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.delete)
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun ExerciseItemPreview() {
    WorkoutTimerScreen {
        ExerciseItem(Exercise("12", "pullups"), 1, {})
    }
}