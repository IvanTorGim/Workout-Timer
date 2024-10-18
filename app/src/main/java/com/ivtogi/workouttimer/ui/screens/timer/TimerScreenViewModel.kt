package com.ivtogi.workouttimer.ui.screens.timer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ivtogi.workouttimer.core.Constants.Companion.ONE_SEC_IN_MILLIS
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
            if (_state.value.isCountdown) {
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
        while (_state.value.actualRound < _state.value.timer.rounds) {
            while (_state.value.actualTime < _state.value.timer.end) {
                delay(ONE_SEC_IN_MILLIS)
                _state.update { it.copy(actualTime = _state.value.actualTime + 1) }
            }
            _state.update { it.copy(actualRound = _state.value.actualRound + 1) }
            if (_state.value.actualRound < _state.value.timer.rounds) {
                delay(ONE_SEC_IN_MILLIS)
                _state.update {
                    it.copy(actualTime = _state.value.timer.initial + 1)
                }
            }
        }
        _state.update { it.copy(isFinished = true) }
    }

    private suspend fun decreaseTime() {
        while (_state.value.actualRound < _state.value.timer.rounds) {
            while (_state.value.actualTime > _state.value.timer.end) {
                delay(ONE_SEC_IN_MILLIS)
                _state.update { it.copy(actualTime = _state.value.actualTime - 1) }
            }
            _state.update { it.copy(actualRound = _state.value.actualRound + 1) }
            if (_state.value.actualRound < _state.value.timer.rounds) {
                delay(ONE_SEC_IN_MILLIS)
                _state.update {
                    it.copy(actualTime = _state.value.timer.initial - 1)
                }
            }
        }
        _state.update { it.copy(isFinished = true) }
    }

    private suspend fun initCountdown() {
        val initialTime = when (_state.value.timer.type) {
            FOR_TIME -> _state.value.timer.initial + 1
            EMOM, AMRAP -> _state.value.timer.initial - 1
        }
        while (_state.value.actualTime > 0) {
            delay(ONE_SEC_IN_MILLIS)
            _state.update { it.copy(actualTime = _state.value.actualTime - 1) }
        }
        delay(ONE_SEC_IN_MILLIS)
        _state.update {
            it.copy(
                isCountdown = false,
                actualTime = initialTime
            )
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
                actualRound = 0,
                isStarted = false,
                isPaused = true,
                isCountdown = true,
                isFinished = false
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
    val isCountdown: Boolean = true,
    val isFinished: Boolean = false,
    val timerJob: Job? = null,
)