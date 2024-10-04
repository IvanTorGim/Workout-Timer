package com.ivtogi.workouttimer.ui.screens.fortime

import androidx.lifecycle.ViewModel
import com.ivtogi.workouttimer.domain.Exercise
import com.ivtogi.workouttimer.ui.formatNumberField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ForTimeViewModel : ViewModel() {
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
}

data class UiState(
    val minutes: String = "",
    val seconds: String = "",
    val workout: List<Exercise> = listOf(),
    val showDialog: Boolean = false,
)