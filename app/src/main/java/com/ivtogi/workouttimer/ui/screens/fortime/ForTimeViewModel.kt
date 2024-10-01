package com.ivtogi.workouttimer.ui.screens.fortime

import androidx.lifecycle.ViewModel
import com.ivtogi.workouttimer.ui.formatNumberField
import com.ivtogi.workouttimer.ui.screens.fortime.UiState.*
import com.ivtogi.workouttimer.ui.screens.fortime.UiState.FormatType.HOURS
import com.ivtogi.workouttimer.ui.screens.fortime.UiState.FormatType.MINUTES
import com.ivtogi.workouttimer.ui.screens.fortime.UiState.FormatType.SECONDS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ForTimeViewModel : ViewModel() {
    private var _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun formatTimerFields(value: String, formatType: FormatType): Unit {
        when (formatType) {
            HOURS -> _state.update { it.copy(hours = value.formatNumberField(99)) }
            MINUTES -> _state.update { it.copy(minutes = value.formatNumberField(60)) }
            SECONDS -> _state.update { it.copy(seconds = value.formatNumberField(60)) }
        }
    }

    fun addExercise(quantity: String, exercise: String) =
        _state.update {
            it.copy(
                workout = it.workout + "- $quantity $exercise",
                showDialog = false
            )
        }

    fun showDialog() = _state.update { it.copy(showDialog = true) }
    fun hideDialog() = _state.update { it.copy(showDialog = false) }
}

data class UiState(
    val hours: String = "",
    val minutes: String = "",
    val seconds: String = "",
    val workout: List<String> = emptyList(),
    val showDialog: Boolean = false
) {
    enum class FormatType {
        HOURS, MINUTES, SECONDS
    }
}