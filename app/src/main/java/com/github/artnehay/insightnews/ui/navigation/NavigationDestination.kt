package com.github.artnehay.insightnews.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.artnehay.insightnews.R

sealed interface NavigationDestination {
    val route: String

    @get:DrawableRes
    val iconId: Int

    @get:StringRes
    val labelId: Int
}

data object ExploreNavigationDestination : NavigationDestination {
    override val route = "explore"
    override val iconId = R.drawable.explore_icon
    override val labelId = R.string.explore_nav_label
}

data object SavedNavigationDestination : NavigationDestination {
    override val route = "saved"
    override val iconId = R.drawable.save_icon_outline
    override val labelId = R.string.saved_nav_label
}
