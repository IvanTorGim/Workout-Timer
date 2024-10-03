package com.ivtogi.workouttimer.ui.screens.timer

import androidx.lifecycle.ViewModel
import com.ivtogi.workouttimer.data.Exercise
import com.ivtogi.workouttimer.data.Timer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TimerScreenViewModel : ViewModel() {

    private var _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()
}

data class UiState(
    val timer: Timer = Timer(
        0,
        61,
        0,
        listOf(
            Exercise("12", "laskjdflk"),
            Exercise("12", "laskjdflk"),
            Exercise("12", "laskjdflk"),
            Exercise("12", "laskjdflk"),
            Exercise("12", "laskjdflk")
        )
    )
)