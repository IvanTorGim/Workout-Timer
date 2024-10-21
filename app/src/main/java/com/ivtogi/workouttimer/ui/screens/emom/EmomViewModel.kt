package com.ivtogi.workouttimer.ui.screens.emom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivtogi.workouttimer.core.toIntTime
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

    fun onTimeChanged(value: String) = _state.update { it.copy(time = value) }

    fun onWorkoutChanged(value: String) = _state.update { it.copy(workout = value) }

    fun onCountdownSelected(value: Int) = _state.update { it.copy(countdown = value) }

    fun onRoundsChanged(value: String) = _state.update {
        it.copy(rounds = value)
    }

    fun saveTimer(navigateToTimer: (Int) -> Unit) {
        val timer = Timer(
            initial = _state.value.time.toIntTime(),
            rounds = _state.value.rounds.toInt(),
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
    val time: String = "00:15",
    val rounds: String = "1",
    val workout: String = "",
    val showDialog: Boolean = false
)