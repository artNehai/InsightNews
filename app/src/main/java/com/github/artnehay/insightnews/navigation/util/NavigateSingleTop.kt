package com.github.artnehay.insightnews.navigation.util

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.github.artnehay.insightnews.navigation.NavigationDestination

fun NavHostController.navigateSingleTop(
    newDestination: NavigationDestination,
) {
    navigate(newDestination.route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = false
        }
        launchSingleTop = true
        restoreState = true
    }
}