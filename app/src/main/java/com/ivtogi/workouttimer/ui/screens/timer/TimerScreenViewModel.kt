package com.ivtogi.workouttimer.ui.screens.timer

import androidx.lifecycle.ViewModel
import com.ivtogi.workouttimer.domain.Exercise
import com.ivtogi.workouttimer.domain.Timer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TimerScreenViewModel @Inject constructor() : ViewModel() {

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