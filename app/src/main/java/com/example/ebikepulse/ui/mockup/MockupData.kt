package com.example.ebikepulse.ui.mockup

object MockupData {
    const val primaryHex = 0xFF00897B

    const val courseName = "한강 방향 코스"
    const val courseDateTime = "2024.05.18 (토) 08:47"

    object Home {
        const val todayPedalKm = "12.4"
        const val todayDistanceKm = "18.2 km"
        const val todayRideTime = "1:05:32"

        const val hrStatus = "HR 연결됨"
        const val hrQuality = "품질 보통"

        const val lastRideTitle = "마지막 라이딩"
        const val lastRideSummary = "실페달 12.4 · 주행 18.2 km · 1:05:32 · 평균 16.8 km/h"

        const val weekProgressTitle = "이번 주"
        const val weekProgressText = "68 / 100 km"
        const val weekProgressRatio = 0.68f
    }

    object Ride {
        const val status = "Recording"
        const val elapsed = "00:42:15"
        const val pedalKm = "5.2"
        const val distanceKm = "7.8 km"
        const val speedKmh = "22.4 km/h"
        const val hrRecent = "128 bpm"

        val tags = listOf("페달만", "스로틀", "혼합")
    }

    object Report {
        const val title = "라이딩 완료"
        const val pedalKm = "12.4"
        const val assistRatio = "68%"
        const val distanceKm = "18.2 km"
        const val time = "1:05:32"

        const val cta = "배터리 V 또는 SOC% 기록"
        const val hrFooter = "HR 연결됨 · 평균 132 bpm · 신뢰도 높음"
    }
}

