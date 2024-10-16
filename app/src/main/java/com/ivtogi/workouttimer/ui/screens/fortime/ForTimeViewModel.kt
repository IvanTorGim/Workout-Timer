package com.ivtogi.workouttimer.ui.screens.fortime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivtogi.workouttimer.core.Constants.Companion.LIMIT_FOR_TIME_AMRAP_MINUTES
import com.ivtogi.workouttimer.core.Constants.Companion.LIMIT_FOR_TIME_AMRAP_SECONDS
import com.ivtogi.workouttimer.core.formatNumberField
import com.ivtogi.workouttimer.core.toIntMinutes
import com.ivtogi.workouttimer.core.toIntSeconds
import com.ivtogi.workouttimer.domain.model.Timer
import com.ivtogi.workouttimer.domain.repository.LocalStorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForTimeViewModel @Inject constructor(
    private val localStorageRepository: LocalStorageRepository
) : ViewModel() {
    private var _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun onMinutesChanged(value: String) = _state.update {
        it.copy(minutes = value.formatNumberField(0, LIMIT_FOR_TIME_AMRAP_MINUTES))
    }

    fun onSecondsChanged(value: String) = _state.update {
        it.copy(seconds = value.formatNumberField(0, LIMIT_FOR_TIME_AMRAP_SECONDS))
    }

    fun onWorkoutChanged(value: String) = _state.update { it.copy(workout = value) }

    fun onCountdownSelected(value: Int) = _state.update { it.copy(countdown = value) }

    fun saveTimer(navigateToTimer: (Int) -> Unit) {
        val endTime = _state.value.minutes.toIntMinutes() + _state.value.seconds.toIntSeconds()
        val timer = Timer(
            end = endTime,
            workout = _state.value.workout.replace(Regex("\\s+"), " "),
            type = Timer.Type.FOR_TIME,
            countdown = _state.value.countdown
        )
        viewModelScope.launch {
            val timerId = localStorageRepository.saveTimer(timer).toInt()
            navigateToTimer(timerId)
        }
    }
}

data class UiState(
    val countdown: Int = Timer.CountDown.TEN.seconds,
    val minutes: String = "",
    val seconds: String = "",
    val rounds: String = "",
    val workout: String = "",
    val showDialog: Boolean = false
)