package com.example.ebikepulse.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DirectionsBike
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ebikepulse.R

enum class TopLevelDestination(
    val route: String,
    @StringRes val labelResId: Int,
    val icon: ImageVector,
) {
    Home("home", R.string.tab_home, Icons.Outlined.Home),
    Challenge("challenge", R.string.tab_challenge, Icons.Outlined.EmojiEvents),
    Ride("ride", R.string.tab_ride, Icons.Outlined.DirectionsBike),
    Records("records", R.string.tab_records, Icons.Outlined.History),
    Settings("settings", R.string.tab_settings, Icons.Outlined.Settings),
}
