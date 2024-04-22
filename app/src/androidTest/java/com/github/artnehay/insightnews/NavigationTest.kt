package com.github.artnehay.insightnews

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
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
            InsightNewsApp(navController = navController)
        }
    }

    @Test
    fun verifyStartDestination() {
        composeTestRule.onNodeWithTag("Explore").assertExists()
        navController.currentDestination?.route shouldBe StartDestination.route
    }

    @Test
    fun clickSavedNavOption_navigatesToSavedScreen() {
        composeTestRule.onNodeWithTagId(SavedNavigationDestination.labelId).performClick()
        navController.currentDestination?.route shouldBe SavedNavigationDestination.route
    }

    @Test
    fun navigateToAnyScreen_popBackStackUpToStartDestination() {
        composeTestRule.onNodeWithTagId(SavedNavigationDestination.labelId).performClick()
        composeTestRule.onNodeWithTagId(StartDestination.labelId).performClick()
        navController.currentBackStack.value.size shouldBe backStackInitialSize
    }

    @Test
    fun verifyLaunchSingleTopBehaviour() {
        composeTestRule.onNodeWithTagId(SavedNavigationDestination.labelId).performClick()
        composeTestRule.onNodeWithTagId(SavedNavigationDestination.labelId).performClick()
        navController.currentBackStack.value.size shouldBe backStackInitialSize + 1
    }

    private val backStackInitialSize = 2
}