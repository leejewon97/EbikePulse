package com.example.ebikepulse.ui.ride

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class RideViewModel : ViewModel() {
    private val _state = MutableStateFlow(RideUiState())
    val state: StateFlow<RideUiState> = _state.asStateFlow()

    private val _effect = Channel<RideEffect>(Channel.BUFFERED)
    val effect: Flow<RideEffect> = _effect.receiveAsFlow()

    private var timerJob: Job? = null

    fun onEvent(event: RideEvent) {
        when (event) {
            RideEvent.OnStartClicked -> onStartClicked()
            RideEvent.OnPauseClicked -> onPauseClicked()
            RideEvent.OnStopClicked -> onStopClicked()
        }
    }

    private fun onStartClicked() {
        when (_state.value.rideStatus) {
            RideStatus.Idle -> {
                _state.update { it.copy(rideStatus = RideStatus.Recording) }
                sendEffect(RideEffect.RequestLocationPermission)
                sendEffect(RideEffect.StartRideService)
                startTimer()
            }
            RideStatus.Paused -> {
                _state.update { it.copy(rideStatus = RideStatus.Recording) }
                startTimer()
            }
            else -> Unit
        }
    }

    private fun onPauseClicked() {
        if (_state.value.rideStatus != RideStatus.Recording) return
        stopTimer()
        _state.update { it.copy(rideStatus = RideStatus.Paused) }
    }

    private fun onStopClicked() {
        when (_state.value.rideStatus) {
            RideStatus.Recording,
            RideStatus.Paused,
            -> {
                stopTimer()
                _state.update { it.copy(rideStatus = RideStatus.Stopping) }
                sendEffect(RideEffect.StopRideService)
                _state.update {
                    RideUiState(rideStatus = RideStatus.Idle)
                }
            }
            else -> Unit
        }
    }

    private fun startTimer() {
        if (timerJob?.isActive == true) return
        timerJob = viewModelScope.launch {
            while (isActive && _state.value.rideStatus == RideStatus.Recording) {
                delay(TIMER_TICK_MS)
                if (_state.value.rideStatus != RideStatus.Recording) break
                _state.update { it.copy(elapsedSeconds = it.elapsedSeconds + 1) }
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    private fun sendEffect(effect: RideEffect) {
        viewModelScope.launch { _effect.send(effect) }
    }

    companion object {
        private const val TIMER_TICK_MS = 1_000L
    }
}
