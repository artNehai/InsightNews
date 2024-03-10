package com.example.android.insightnews.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class NavigationItem(
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
)