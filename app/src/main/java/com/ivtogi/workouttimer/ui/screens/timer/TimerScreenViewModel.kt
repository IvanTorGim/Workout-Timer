package com.ivtogi.workouttimer.ui.screens.timer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ivtogi.workouttimer.domain.model.Timer
import com.ivtogi.workouttimer.domain.model.Timer.Type.AMRAP
import com.ivtogi.workouttimer.domain.model.Timer.Type.EMOM
import com.ivtogi.workouttimer.domain.model.Timer.Type.FOR_TIME
import com.ivtogi.workouttimer.domain.repository.LocalStorageRepository
import com.ivtogi.workouttimer.ui.screens.navigation.TimerRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val localStorageRepository: LocalStorageRepository
) : ViewModel() {

    private var _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val timerRoute = savedStateHandle.toRoute<TimerRoute>()
            val timer = localStorageRepository.getTimerById(timerRoute.id)
            _state.update { it.copy(timer = timer, actualTime = timer.countdown) }
        }
    }

    fun startTimer() {
        _state.value.timerJob?.cancel()
        _state.update { it.copy(isPaused = false) }
        val timerJob = viewModelScope.launch {
            if (_state.value.timer.countdown > 0) {
                _state.update { it.copy(isStarted = true) }
                initCountdown()
            }
            when (_state.value.timer.type) {
                FOR_TIME -> increaseTime()
                EMOM -> decreaseTime()
                AMRAP -> decreaseTime()
            }
        }
        _state.update { it.copy(timerJob = timerJob) }
    }

    private suspend fun increaseTime() {
        for (round in 1.._state.value.timer.rounds) {
            _state.update { it.copy(actualRound = round, actualTime = _state.value.timer.initial) }
            while (_state.value.actualTime < _state.value.timer.end) {
                _state.update { it.copy(actualTime = _state.value.actualTime + 1) }
                delay(1000)
            }
        }
    }

    private suspend fun decreaseTime() {
        for (round in 1.._state.value.timer.rounds) {
            _state.update { it.copy(actualRound = round, actualTime = _state.value.timer.initial) }
            while (_state.value.actualTime > _state.value.timer.end) {
                _state.update { it.copy(actualTime = _state.value.actualTime - 1) }
                delay(1000)
            }
        }
    }

    private suspend fun initCountdown() {
        while (_state.value.actualTime > 0) {
            _state.update { it.copy(actualTime = _state.value.actualTime - 1) }
            delay(1000)
        }
    }

    fun pauseTimer() {
        _state.value.timerJob?.cancel()
        _state.update { it.copy(isPaused = true) }
    }

    fun resetTimer() {
        _state.value.timerJob?.cancel()
        _state.update {
            it.copy(
                actualTime = _state.value.timer.countdown,
                isPaused = true,
                isStarted = false
            )
        }
    }
}

data class UiState(
    val timer: Timer = Timer(),
    val actualTime: Int = 0,
    val actualRound: Int = 0,
    val isStarted: Boolean = false,
    val isPaused: Boolean = true,
    val timerJob: Job? = null,
)