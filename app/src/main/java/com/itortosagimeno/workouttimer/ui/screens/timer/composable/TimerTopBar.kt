package com.itortosagimeno.workouttimer.ui.screens.timer.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.itortosagimeno.workouttimer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerTopBar(
    @StringRes title: Int,
    timer: String = "",
    rounds: String = "",
    isPaused: Boolean,
    isStarted: Boolean,
    onBackClick: () -> Unit,
    onPlayClick: () -> Unit,
    onPauseClick: () -> Unit,
    onResetClick: () -> Unit
) {
    TopAppBar(
        title = {
            Row {
                Text(text = stringResource(id = title))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = timer)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = rounds)
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.go_back)
                )
            }
        },
        actions = {
            if (isPaused) {
                if (isStarted) {
                    IconButton(onClick = onResetClick) {
                        Icon(
                            imageVector = Icons.Default.Restore,
                            contentDescription = stringResource(R.string.reset_timer),
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                IconButton(onClick = onPlayClick) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = stringResource(R.string.play_timer),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            } else {
                IconButton(onClick = onPauseClick) {
                    Icon(
                        imageVector = Icons.Default.Pause,
                        contentDescription = stringResource(R.string.pause_timer),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    )
}