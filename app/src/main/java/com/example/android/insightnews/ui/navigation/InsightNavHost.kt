package com.example.android.insightnews.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android.insightnews.ui.screens.ExploreScreen
import com.example.android.insightnews.ui.screens.SavedScreen

@Composable
fun InsightNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = ExploreNavigationDestination.route,
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