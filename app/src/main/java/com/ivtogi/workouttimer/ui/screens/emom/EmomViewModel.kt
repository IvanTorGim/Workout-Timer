package com.ivtogi.workouttimer.ui.screens.emom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivtogi.workouttimer.core.Constants.Companion.LIMIT_EMOM_MINUTES
import com.ivtogi.workouttimer.core.Constants.Companion.LIMIT_EMOM_SECONDS
import com.ivtogi.workouttimer.core.Constants.Companion.LIMIT_ROUNDS
import com.ivtogi.workouttimer.core.formatNumberField
import com.ivtogi.workouttimer.core.toIntMinutes
import com.ivtogi.workouttimer.core.toIntRounds
import com.ivtogi.workouttimer.core.toIntSeconds
import com.ivtogi.workouttimer.domain.model.Timer
import com.ivtogi.workouttimer.domain.repository.LocalStorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmomViewModel @Inject constructor(
    private val localStorageRepository: LocalStorageRepository
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    var state = _state.asStateFlow()

    fun onMinutesChanged(value: String) = _state.update {
        it.copy(minutes = value.formatNumberField(0, LIMIT_EMOM_MINUTES))
    }

    fun onSecondsChanged(value: String) = _state.update {
        it.copy(seconds = value.formatNumberField(0, LIMIT_EMOM_SECONDS))
    }

    fun onWorkoutChanged(value: String) = _state.update { it.copy(workout = value) }

    fun onCountdownSelected(value: Int) = _state.update { it.copy(countdown = value) }

    fun onRoundsChanged(value: String) = _state.update {
        it.copy(rounds = value.formatNumberField(1, LIMIT_ROUNDS))
    }

    fun saveTimer(navigateToTimer: (Int) -> Unit) {
        val initialTime = _state.value.minutes.toIntMinutes() + _state.value.seconds.toIntSeconds()
        val timer = Timer(
            initial = initialTime,
            rounds = _state.value.rounds.toIntRounds(),
            workout = _state.value.workout.replace(Regex("\\s+"), " "),
            type = Timer.Type.EMOM,
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