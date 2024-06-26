package com.github.artnehay.insightnews

import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.github.artnehay.insightnews.ui.InsightNewsApp
import com.github.artnehay.insightnews.ui.navigation.NavigationDestination.SavedNavigationDestination
import com.github.artnehay.insightnews.ui.navigation.StartDestination
import io.kotest.matchers.shouldBe
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupInsightNavHost() {
        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            InsightNewsApp(
                windowWidth = WindowWidthSizeClass.Compact,
                navController = navController,
            )
        }
    }

    @Test
    fun verifyStartDestination() {
        composeTestRule.onNodeWithTagId(StartDestination.testTitleId).assertExists()
        navController.currentDestination?.route shouldBe StartDestination.route
    }

    @Test
    fun clickSavedNavOption_navigatesToSavedScreen() {
        composeTestRule.onNodeWithTagId(SavedNavigationDestination.testLabelId).performClick()
        navController.currentDestination?.route shouldBe SavedNavigationDestination.route
    }

    @Test
    fun navigateToAnyScreen_popBackStackUpToStartDestination() {
        composeTestRule.onNodeWithTagId(SavedNavigationDestination.testLabelId).performClick()
        composeTestRule.onNodeWithTagId(StartDestination.testLabelId).performClick()
        navController.currentBackStack.value.size shouldBe backStackInitialSize
    }

    @Test
    fun verifyLaunchSingleTopBehaviour() {
        composeTestRule.onNodeWithTagId(SavedNavigationDestination.testLabelId).performClick()
        composeTestRule.onNodeWithTagId(SavedNavigationDestination.testLabelId).performClick()
        navController.currentBackStack.value.size shouldBe backStackInitialSize + 1
    }

    private val backStackInitialSize = 2
}