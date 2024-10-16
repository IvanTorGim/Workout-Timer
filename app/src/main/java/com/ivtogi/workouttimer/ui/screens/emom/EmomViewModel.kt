package com.ivtogi.workouttimer.ui.screens.emom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivtogi.workouttimer.core.Constants.Companion.LIMIT_EMOM_MINUTES
import com.ivtogi.workouttimer.core.Constants.Companion.LIMIT_EMOM_SECONDS
import com.ivtogi.workouttimer.core.Constants.Companion.LIMIT_ROUNDS
import com.ivtogi.workouttimer.core.formatNumberField
import com.ivtogi.workouttimer.core.toIntMinutes
import com.ivtogi.workouttimer.core.toIntSeconds
import com.ivtogi.workouttimer.domain.model.Exercise
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
        it.copy(minutes = value.formatNumberField(LIMIT_EMOM_MINUTES))
    }

    fun onSecondsChanged(value: String) = _state.update {
        it.copy(seconds = value.formatNumberField(LIMIT_EMOM_SECONDS))
    }

    fun addExercise(exercise: Exercise) = _state.update {
        it.copy(workout = it.workout + exercise, showDialog = false)
    }

    fun deleteExercise(index: Int) {
        val list = _state.value.workout.toMutableList()
        list.removeAt(index)
        _state.update { it.copy(workout = list) }
    }

    fun onCountdownSelected(value: Int) = _state.update { it.copy(countdown = value) }

    fun onRoundsChanged(value: String) = _state.update {
        it.copy(rounds = value.formatNumberField(LIMIT_ROUNDS))
    }

    fun showDialog() = _state.update { it.copy(showDialog = true) }
    fun hideDialog() = _state.update { it.copy(showDialog = false) }

    fun saveTimer(navigateToTimer: (Int) -> Unit) {
        val initialTime = _state.value.minutes.toIntMinutes() + _state.value.seconds.toIntSeconds()
        val timer =
            Timer(
                initial = initialTime,
                workout = _state.value.workout,
                type = Timer.Type.AMRAP,
                countdown = _state.value.countdown
            )
        viewModelScope.launch {
            val timerId = localStorageRepository.saveTimerWithExercises(timer).toInt()
            navigateToTimer(timerId)
        }
    }
}

data class UiState(
    val countdown: Int = Timer.CountDown.TEN.seconds,
    val minutes: String = "",
    val seconds: String = "",
    val rounds: String = "",
    val workout: List<Exercise> = emptyList(),
    val showDialog: Boolean = false
)