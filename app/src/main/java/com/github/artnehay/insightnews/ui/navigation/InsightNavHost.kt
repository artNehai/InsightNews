package com.github.artnehay.insightnews.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.artnehay.insightnews.feature.explore.ExploreScreen
import com.github.artnehay.insightnews.feature.saved.SavedScreen
import com.github.artnehay.insightnews.ui.navigation.NavigationDestination.ExploreNavigationDestination
import com.github.artnehay.insightnews.ui.navigation.NavigationDestination.SavedNavigationDestination

@Composable
fun InsightNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = StartDestination.route,
        modifier = modifier,
    ) {
        composable(route = ExploreNavigationDestination.route) {
            ExploreScreen()
        }
        composable(route = SavedNavigationDestination.route) {
            SavedScreen()
        }
    }
}

val StartDestination = ExploreNavigationDestination