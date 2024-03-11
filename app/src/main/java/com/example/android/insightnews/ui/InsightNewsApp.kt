package com.example.android.insightnews.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.android.insightnews.R
import com.example.android.insightnews.ui.navigation.BottomNavBar
import com.example.android.insightnews.ui.navigation.InsightNavHost
import com.example.android.insightnews.ui.theme.InsightNewsTheme

@Composable
fun InsightNewsApp() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                onItemClick = { newDestination ->
                    navController.navigate(newDestination.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
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

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true,
)
@Composable
private fun ExploreScreenPreview() {
    InsightNewsTheme {
        InsightNewsApp()
    }
}