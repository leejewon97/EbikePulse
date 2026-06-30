package com.example.ebikepulse.ui.mockup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ebikepulse.navigation.TopLevelDestination

@Composable
fun MockupRideMockup(modifier: Modifier = Modifier) {
    MockupTheme {
        MockupPhoneFrame(modifier = modifier) {
            MockupRideContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MockupRideContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Ebike Pulse",
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
            )
        },
        bottomBar = { MockupBottomBar(selected = TopLevelDestination.Ride) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = MockupData.Ride.status,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = MockupData.Ride.elapsed,
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "실페달 km",
                    style = MaterialTheme.typography.labelLarge,
                )
                Text(
                    text = MockupData.Ride.pedalKm,
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            MetricChipRow(
                items = listOf(
                    "주행거리" to MockupData.Ride.distanceKm,
                    "현재 속도" to MockupData.Ride.speedKmh,
                ),
            )
            InfoSurfaceCard(
                title = "심박수",
                value = MockupData.Ride.hrRecent,
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "오늘 누적",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                InfoSurfaceCard(title = "실페달", value = "12.4 km")
                InfoSurfaceCard(title = "주행거리", value = "18.2 km")
            }
            TagChipsRow(tags = MockupData.Ride.tags)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                ) {
                    Text(text = "일시정지")
                }
                Button(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                ) {
                    Text(text = "종료")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
