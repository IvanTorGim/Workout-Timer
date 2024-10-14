package com.ivtogi.workouttimer.ui.screens.timer.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun TimerIconButton(
    icon: ImageVector,
    @StringRes contentDescription: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(120.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = contentDescription),
            modifier = Modifier.fillMaxSize()
        )
    }
}