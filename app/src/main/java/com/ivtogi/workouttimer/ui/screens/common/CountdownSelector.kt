package com.ivtogi.workouttimer.ui.screens.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.domain.model.Timer

@Composable
fun CountdownSelector(
    seconds: Int,
    onSelected: (Int) -> Unit
) {
    Text(
        text = stringResource(id = R.string.countdown_selector),
        style = MaterialTheme.typography.titleLarge
    )
    Spacer(modifier = Modifier.height(8.dp))
    SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
        Timer.CountDown.entries.forEachIndexed { index, countdown ->
            SegmentedButton(
                selected = seconds == countdown.seconds,
                onClick = { onSelected(countdown.seconds) },
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = Timer.CountDown.entries.size
                )
            ) {
                Text(text = countdown.seconds.toString())
            }
        }
    }
}