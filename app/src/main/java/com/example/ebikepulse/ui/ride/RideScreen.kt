package com.example.ebikepulse.ui.ride

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ebikepulse.R
import com.example.ebikepulse.ui.theme.EbikePulseTheme

private const val TAG = "RideScreen"

@Composable
fun RideScreen(
    modifier: Modifier = Modifier,
    viewModel: RideViewModel = viewModel(),
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                RideEffect.RequestLocationPermission -> {
                    Log.d(TAG, "Effect: RequestLocationPermission")
                }
                RideEffect.StartRideService -> {
                    Log.d(TAG, "Effect: StartRideService")
                }
                RideEffect.StopRideService -> {
                    Log.d(TAG, "Effect: StopRideService")
                }
            }
        }
    }

    RideContent(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        modifier = modifier,
    )
}

@Composable
private fun RideContent(
    uiState: RideUiState,
    onEvent: (RideEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(
                text = rideStatusLabel(uiState.rideStatus),
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = formatElapsed(uiState.elapsedSeconds),
                style = MaterialTheme.typography.headlineSmall,
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = stringResource(R.string.ride_pedal_km_label),
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                text = stringResource(R.string.ride_metric_km, uiState.pedalKm),
                style = MaterialTheme.typography.displayMedium,
            )
            Spacer(modifier = Modifier.height(16.dp))
            MetricRow(
                label = stringResource(R.string.ride_distance_km_label),
                value = stringResource(R.string.ride_metric_km, uiState.distanceKm),
            )
            MetricRow(
                label = stringResource(R.string.ride_speed_kmh_label),
                value = stringResource(R.string.ride_metric_speed, uiState.speedKmh),
            )
        }

        RideActionButtons(
            rideStatus = uiState.rideStatus,
            onEvent = onEvent,
        )
    }
}

@Composable
private fun MetricRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun RideActionButtons(
    rideStatus: RideStatus,
    onEvent: (RideEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
    ) {
        when (rideStatus) {
            RideStatus.Idle -> {
                Button(onClick = { onEvent(RideEvent.OnStartClicked) }) {
                    Text(text = stringResource(R.string.ride_start))
                }
            }
            RideStatus.Recording -> {
                Button(onClick = { onEvent(RideEvent.OnPauseClicked) }) {
                    Text(text = stringResource(R.string.ride_pause))
                }
                Button(onClick = { onEvent(RideEvent.OnStopClicked) }) {
                    Text(text = stringResource(R.string.ride_stop))
                }
            }
            RideStatus.Paused -> {
                Button(onClick = { onEvent(RideEvent.OnStartClicked) }) {
                    Text(text = stringResource(R.string.ride_resume))
                }
                Button(onClick = { onEvent(RideEvent.OnStopClicked) }) {
                    Text(text = stringResource(R.string.ride_stop))
                }
            }
            RideStatus.Stopping -> {
                Text(
                    text = stringResource(R.string.ride_status_stopping),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Composable
private fun rideStatusLabel(rideStatus: RideStatus): String {
    return when (rideStatus) {
        RideStatus.Idle -> stringResource(R.string.ride_status_idle)
        RideStatus.Recording -> stringResource(R.string.ride_status_recording)
        RideStatus.Paused -> stringResource(R.string.ride_status_paused)
        RideStatus.Stopping -> stringResource(R.string.ride_status_stopping)
    }
}

@Composable
private fun formatElapsed(elapsedSeconds: Long): String {
    val minutes = elapsedSeconds / 60
    val seconds = elapsedSeconds % 60
    return stringResource(R.string.ride_elapsed_time, minutes, seconds)
}

@Preview(showBackground = true)
@Composable
private fun RideContentRecordingPreview() {
    EbikePulseTheme {
        RideContent(
            uiState = RideUiState(
                rideStatus = RideStatus.Recording,
                elapsedSeconds = 125,
            ),
            onEvent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RideContentIdlePreview() {
    EbikePulseTheme {
        RideContent(
            uiState = RideUiState(),
            onEvent = {},
        )
    }
}
