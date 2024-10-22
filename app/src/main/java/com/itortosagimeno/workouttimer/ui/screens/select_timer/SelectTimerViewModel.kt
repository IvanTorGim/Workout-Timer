package com.itortosagimeno.workouttimer.ui.screens.select_timer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.itortosagimeno.workouttimer.core.navigation.SelectorRoute
import com.itortosagimeno.workouttimer.core.toIntTime
import com.itortosagimeno.workouttimer.domain.model.Timer
import com.itortosagimeno.workouttimer.domain.repository.LocalStorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectTimerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val localStorageRepository: LocalStorageRepository
) : ViewModel() {
    private var _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val selectorRoute = savedStateHandle.toRoute<SelectorRoute>()
            _state.update { it.copy(type = Timer.Type.entries.first { entries -> entries.value == selectorRoute.type }) }
        }
    }

    fun onTimeChanged(value: String) = _state.update { it.copy(time = value) }

    fun onWorkoutChanged(value: String) = _state.update { it.copy(workout = value) }

    fun onCountdownSelected(value: Int) = _state.update { it.copy(countdown = value) }

    fun onRoundsChanged(value: String) = _state.update { it.copy(rounds = value) }

    fun saveTimer(navigateToTimer: (Int) -> Unit) {
        var initial = 0
        var end = 0

        when (_state.value.type) {
            Timer.Type.FOR_TIME -> end = _state.value.time.toIntTime()
            Timer.Type.EMOM, Timer.Type.AMRAP -> initial = _state.value.time.toIntTime()
        }

        val timer = Timer(
            initial = initial,
            end = end,
            rounds = _state.value.rounds.toInt(),
            workout = _state.value.workout.replace(Regex("\\s+"), " "),
            type = _state.value.type,
            countdown = _state.value.countdown
        )
        viewModelScope.launch {
            val timerId = localStorageRepository.saveTimer(timer).toInt()
            navigateToTimer(timerId)
        }
    }
}

data class UiState(
    val type: Timer.Type = Timer.Type.FOR_TIME,
    val countdown: Int = Timer.CountDown.TEN.seconds,
    val time: String = "01:00",
    val rounds: String = "1",
    val workout: String = ""
)