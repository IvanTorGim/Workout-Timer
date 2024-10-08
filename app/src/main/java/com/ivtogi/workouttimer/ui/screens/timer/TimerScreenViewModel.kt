package com.ivtogi.workouttimer.ui.screens.timer

import androidx.lifecycle.ViewModel
import com.ivtogi.workouttimer.domain.model.Exercise
import com.ivtogi.workouttimer.domain.model.Timer
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
        initial = 0,
        end = 61,
        rounds = 0,
        workout = listOf(
            Exercise(quantity = "12", name = "laskjdflk"),
            Exercise(quantity = "12", name = "laskjdflk"),
            Exercise(quantity = "12", name = "laskjdflk"),
            Exercise(quantity = "12", name = "laskjdflk"),
            Exercise(quantity = "12", name = "laskjdflk")
        )
    )
)