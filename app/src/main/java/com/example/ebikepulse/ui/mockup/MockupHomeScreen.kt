package com.example.ebikepulse.ui.mockup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ebikepulse.R
import com.example.ebikepulse.navigation.TopLevelDestination

@Composable
fun MockupHomeMockup(modifier: Modifier = Modifier) {
    MockupTheme {
        MockupPhoneFrame(modifier = modifier) {
            MockupHomeContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MockupHomeContent() {
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
        bottomBar = { MockupBottomBar(selected = TopLevelDestination.Home) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            KpiHeroCard(
                title = "오늘 실페달",
                value = MockupData.Home.todayPedalKm,
                unit = "km",
            )
            HrStatusRow(
                hrText = MockupData.Home.hrStatus,
                qualityText = MockupData.Home.hrQuality,
            )
            MetricChipRow(
                items = listOf(
                    "주행거리" to MockupData.Home.todayDistanceKm,
                    "라이딩 시간" to MockupData.Home.todayRideTime,
                ),
            )
            SimpleCard(
                title = MockupData.Home.lastRideTitle,
                body = {
                    MockupMapCard(
                        mapRes = R.drawable.mockup_map_bg_home,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp),
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = MockupData.Home.lastRideSummary,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${MockupData.courseName} · ${MockupData.courseDateTime}",
                        style = MaterialTheme.typography.bodySmall,
                    )
                },
            )
            SimpleCard(
                title = MockupData.Home.weekProgressTitle,
                body = {
                    ProgressRow(
                        leftText = MockupData.Home.weekProgressText,
                        ratio = MockupData.Home.weekProgressRatio,
                        hint = "주간 챌린지 68 / 100 km",
                    )
                },
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
