package com.github.artnehay.insightnews

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.rules.ActivityScenarioRule

fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.onNodeWithTagId(
    testTagId: Int,
): SemanticsNodeInteraction {
    val tag = this.activity.getString(testTagId)
    return onNodeWithTag(tag)
}