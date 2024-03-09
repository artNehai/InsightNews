package com.example.android.insightnews.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.android.insightnews.R
import com.example.android.insightnews.ui.screens.ExploreScreen
import com.example.android.insightnews.ui.theme.InsightNewsTheme

@Composable
fun InsightNewsApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavBar() },
        contentWindowInsets = WindowInsets(
            left = dimensionResource(R.dimen.horizontal_screen_margin),
            top = dimensionResource(R.dimen.top_screen_margin),
            right = dimensionResource(R.dimen.horizontal_screen_margin),
        )
    ) { insetsPadding ->
        ExploreScreen(
            modifier = Modifier
                .padding(insetsPadding)
                .consumeWindowInsets(insetsPadding)
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