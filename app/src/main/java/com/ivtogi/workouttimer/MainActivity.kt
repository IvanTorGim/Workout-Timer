package com.ivtogi.workouttimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ivtogi.workouttimer.ui.WorkTimerApp
import com.ivtogi.workouttimer.ui.screens.fortime.ForTimeScreen
import com.ivtogi.workouttimer.ui.screens.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkTimerApp {
                ForTimeScreen()
            }
        }
    }
}

