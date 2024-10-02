package com.ivtogi.workouttimer.ui.screens.emom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.ui.screens.common.TopBar

@Composable
fun EmomScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopBar(
                title = R.string.emom,
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Text(text = "EMOM")
        }
    }
}