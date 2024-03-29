package com.github.artnehay.insightnews.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.artnehay.insightnews.R
import com.github.artnehay.insightnews.core.ui.R.drawable.explore_icon
import com.github.artnehay.insightnews.core.ui.R.drawable.save_icon_outline

enum class NavigationDestination(
    val route: String,

    @get:DrawableRes
    val iconId: Int,

    @get:StringRes
    val labelId: Int,

    @get:StringRes
    val titleId: Int,
) {
    ExploreNavigationDestination(
        route = "explore",
        iconId = explore_icon,
        labelId = R.string.explore_nav_label,
        titleId = R.string.explore_screen_title,
    ),
    SavedNavigationDestination(
        route = "saved",
        iconId = save_icon_outline,
        labelId = R.string.saved_nav_label,
        titleId = R.string.saved_screen_title,
    )
}