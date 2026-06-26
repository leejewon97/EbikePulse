package com.example.ebikepulse

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ebikepulse.navigation.TopLevelDestination
import com.example.ebikepulse.ui.challenge.ChallengeScreen
import com.example.ebikepulse.ui.home.HomeScreen
import com.example.ebikepulse.ui.records.RecordsScreen
import com.example.ebikepulse.ui.ride.RideScreen
import com.example.ebikepulse.ui.settings.SettingsScreen

@Composable
fun EbikePulseApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                TopLevelDestination.entries.forEach { destination ->
                    NavigationBarItem(
                        selected = currentRoute == destination.route,
                        onClick = {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = stringResource(destination.labelResId),
                            )
                        },
                        label = { Text(text = stringResource(destination.labelResId)) },
                    )
                }
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = TopLevelDestination.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(TopLevelDestination.Home.route) {
                HomeScreen()
            }
            composable(TopLevelDestination.Challenge.route) {
                ChallengeScreen()
            }
            composable(TopLevelDestination.Ride.route) {
                RideScreen()
            }
            composable(TopLevelDestination.Records.route) {
                RecordsScreen()
            }
            composable(TopLevelDestination.Settings.route) {
                SettingsScreen()
            }
        }
    }
}
