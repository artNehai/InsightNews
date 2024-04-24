package com.github.artnehay.insightnews.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.artnehay.insightnews.R
import com.github.artnehay.insightnews.ui.navigation.BottomNavBar
import com.github.artnehay.insightnews.ui.navigation.InsightNavHost
import com.github.artnehay.insightnews.ui.navigation.util.topLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsightNewsApp(
    navController: NavHostController = rememberNavController(),
) {
    val currentDestination =
        navController.currentBackStackEntryAsState().value.topLevelDestination()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(currentDestination.titleId),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.testTag(stringResource(currentDestination.testTitleId))
                    )
                },
            )
        },
        bottomBar = {
            BottomNavBar(
                currentDestination = currentDestination,
                onItemClick = { newDestination ->
                    navController.navigate(newDestination.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = false
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets(
            left = dimensionResource(R.dimen.horizontal_screen_margin),
            top = dimensionResource(R.dimen.top_screen_margin),
            right = dimensionResource(R.dimen.horizontal_screen_margin),
        )
    ) { insetsPadding ->

        InsightNavHost(
            navController = navController,
            modifier = Modifier.padding(insetsPadding),
        )
    }
}