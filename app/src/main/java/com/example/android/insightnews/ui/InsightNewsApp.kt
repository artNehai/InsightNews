package com.example.android.insightnews.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.android.insightnews.R
import com.example.android.insightnews.ui.screens.ExploreScreen

@Composable
fun InsightNewsApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(
            left = dimensionResource(R.dimen.screen_margin),
            right = dimensionResource(R.dimen.screen_margin),
        )
    ) { insetsPadding ->
        ExploreScreen(
            modifier = Modifier
                .padding(insetsPadding)
                .consumeWindowInsets(insetsPadding)
        )
    }
}