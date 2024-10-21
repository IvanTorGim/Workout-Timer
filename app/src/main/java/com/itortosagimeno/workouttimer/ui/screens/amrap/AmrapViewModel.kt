package com.itortosagimeno.workouttimer.ui.screens.amrap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itortosagimeno.workouttimer.core.toIntTime
import com.itortosagimeno.workouttimer.domain.model.Timer
import com.itortosagimeno.workouttimer.domain.repository.LocalStorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmrapViewModel @Inject constructor(
    private val localStorageRepository: LocalStorageRepository
) : ViewModel() {
    private var _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun onTimeChanged(value: String) = _state.update { it.copy(time = value) }

    fun onWorkoutChanged(value: String) = _state.update { it.copy(workout = value) }

    fun onCountdownSelected(value: Int) = _state.update { it.copy(countdown = value) }

    fun saveTimer(navigateToTimer: (Int) -> Unit) {
        val timer = Timer(
            initial = _state.value.time.toIntTime(),
            workout = _state.value.workout.replace(Regex("\\s+"), " "),
            type = Timer.Type.AMRAP,
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
    val time: String = "01:00",
    val rounds: Int = 1,
    val workout: String = "",
    val showDialog: Boolean = false
)