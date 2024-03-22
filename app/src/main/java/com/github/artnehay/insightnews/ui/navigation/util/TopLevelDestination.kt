package com.github.artnehay.insightnews.ui.navigation.util

import androidx.navigation.NavBackStackEntry
import com.github.artnehay.insightnews.ui.navigation.NavigationDestination
import com.github.artnehay.insightnews.ui.navigation.StartDestination

fun NavBackStackEntry?.topLevelDestination(): NavigationDestination {
    val route = this?.destination?.route
    return (NavigationDestination.entries.firstOrNull { it.route == route }) ?: StartDestination
}