package com.ivtogi.workouttimer.ui.screens.amrap

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ivtogi.workouttimer.R
import com.ivtogi.workouttimer.ui.screens.common.TopBar

@Composable
fun AmrapScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopBar(
                title = R.string.amrap,
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Text(text = "AMRAP")
        }
    }
}