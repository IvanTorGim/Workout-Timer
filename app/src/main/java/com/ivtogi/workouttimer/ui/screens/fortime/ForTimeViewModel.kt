package com.ivtogi.workouttimer.ui.screens.fortime

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivtogi.workouttimer.domain.model.Exercise
import com.ivtogi.workouttimer.domain.model.Timer
import com.ivtogi.workouttimer.domain.repository.LocalStorageRepository
import com.ivtogi.workouttimer.ui.formatNumberField
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
        it.copy(minutes = value.formatNumberField(60))
    }

    fun onSecondsChanged(value: String) = _state.update {
        it.copy(seconds = value.formatNumberField(60))
    }

    fun addExercise(exercise: Exercise) = _state.update {
        it.copy(workout = it.workout + exercise, showDialog = false)
    }

    fun deleteExercise(index: Int) {
        val list = _state.value.workout.toMutableList()
        list.removeAt(index)
        _state.update { it.copy(workout = list) }
    }

    fun showDialog() = _state.update { it.copy(showDialog = true) }
    fun hideDialog() = _state.update { it.copy(showDialog = false) }

    private fun formatMinutes(): Int =
        if (_state.value.minutes.isEmpty()) 0 else _state.value.minutes.toInt() * 60

    private fun formatSeconds(): Int =
        if (_state.value.minutes.isEmpty()) 0 else _state.value.minutes.toInt()

    fun saveTimer() {
        val endTime = formatMinutes() * formatSeconds()
        val timer = Timer(end = endTime, workout = _state.value.workout)
        viewModelScope.launch {
            localStorageRepository.saveTimerWithExercises(timer)
        }
    }
}

data class UiState(
    val minutes: String = "",
    val seconds: String = "",
    val workout: List<Exercise> = listOf(),
    val showDialog: Boolean = false,
)