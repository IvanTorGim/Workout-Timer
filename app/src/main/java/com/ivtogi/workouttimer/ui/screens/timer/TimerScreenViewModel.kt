package com.ivtogi.workouttimer.ui.screens.timer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ivtogi.workouttimer.domain.model.Timer
import com.ivtogi.workouttimer.domain.repository.LocalStorageRepository
import com.ivtogi.workouttimer.ui.screens.navigation.TimerRoute
import dagger.hilt.android.lifecycle.HiltViewModel
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
            val timer = localStorageRepository.getTimerWithExercisesById(timerRoute.id)
            _state.update { it.copy(timer = timer) }
        }
    }

}

data class UiState(
    val timer: Timer = Timer()
)