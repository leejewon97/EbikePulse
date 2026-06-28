package com.example.ebikepulse.ui.ride

enum class RideStatus {
    Idle,
    Recording,
    Paused,
    Stopping,
}

data class RideUiState(
    val rideStatus: RideStatus = RideStatus.Idle,
    val elapsedSeconds: Long = 0,
    val pedalKm: Double = 0.0,
    val distanceKm: Double = 0.0,
    val speedKmh: Double = 0.0,
)

sealed interface RideEvent {
    data object OnStartClicked : RideEvent

    data object OnPauseClicked : RideEvent

    data object OnStopClicked : RideEvent
}

sealed interface RideEffect {
    data object RequestLocationPermission : RideEffect

    data object StartRideService : RideEffect

    data object StopRideService : RideEffect
}
