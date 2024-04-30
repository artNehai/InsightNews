package com.github.artnehay.insightnews.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.artnehay.insightnews.R
import com.github.artnehay.insightnews.ui.navigation.InsightNavHost
import com.github.artnehay.insightnews.ui.navigation.NavigationDestination
import com.github.artnehay.insightnews.ui.navigation.components.BottomNavBar
import com.github.artnehay.insightnews.ui.navigation.components.InsightNavDrawer
import com.github.artnehay.insightnews.ui.navigation.components.InsightNavigationRail
import com.github.artnehay.insightnews.ui.navigation.util.navigateSingleTop
import com.github.artnehay.insightnews.ui.navigation.util.topLevelDestination

@Composable
fun InsightNewsApp(
    windowWidth: WindowWidthSizeClass,
    navController: NavHostController = rememberNavController(),
) {
    val currentDestination =
        navController.currentBackStackEntryAsState().value.topLevelDestination()

    when (windowWidth) {

        WindowWidthSizeClass.Compact -> {
            ContentPane(
                destination = currentDestination,
                navController = navController,
                isBottomBarPresent = true,
            )
        }

        WindowWidthSizeClass.Medium -> {
            Row {
                InsightNavigationRail(
                    currentDestination = currentDestination,
                    onItemClick = { navController.navigateSingleTop(it) }
                )
                ContentPane(
                    destination = currentDestination,
                    navController = navController
                )
            }
        }

        WindowWidthSizeClass.Expanded -> {
            InsightNavDrawer(
                currentDestination = currentDestination,
                onItemClick = { navController.navigateSingleTop(it) },
            ) {
                ContentPane(
                    destination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentPane(
    destination: NavigationDestination,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    isBottomBarPresent: Boolean = false,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(destination.titleId),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.testTag(stringResource(destination.testTitleId))
                    )
                },
            )
        },
        bottomBar = {
            if (isBottomBarPresent) {
                BottomNavBar(
                    currentDestination = destination,
                    onItemClick = { navController.navigateSingleTop(it) }
                )
            }
        },
        contentWindowInsets = WindowInsets(
            left = dimensionResource(R.dimen.horizontal_screen_margin),
            right = dimensionResource(R.dimen.horizontal_screen_margin),
        )
    ) { insetsPadding ->

        InsightNavHost(
            navController = navController,
            modifier = Modifier.padding(insetsPadding),
        )
    }
}