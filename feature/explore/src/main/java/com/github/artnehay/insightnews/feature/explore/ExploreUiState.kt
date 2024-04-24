package com.github.artnehay.insightnews.feature.explore

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import com.github.artnehay.insightnews.core.model.Article

sealed interface ExploreUiState {
    data object Loading : ExploreUiState

    @Stable
    data class Success(
        val topHeadlines: List<Article> = listOf(),
        val urlToTimeCaption: Map<String, String> = mapOf(),
    ) : ExploreUiState

    data class Error(
        @DrawableRes val errorIconId: Int,
        @StringRes val message: Int,
    ) : ExploreUiState
}