package com.ivtogi.workouttimer.ui.screens.timer

import androidx.lifecycle.ViewModel
import com.ivtogi.workouttimer.data.Exercise
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TimerScreenViewModel : ViewModel() {

    private var _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()
}

data class UiState(
    val minutes: String = "",
    val seconds: String = "",
    val workout: List<Exercise> = listOf(
        Exercise("12", "pullups"),
        Exercise("12", "flexiones")
    )
)