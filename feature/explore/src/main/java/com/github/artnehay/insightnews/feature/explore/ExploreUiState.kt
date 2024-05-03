package com.github.artnehay.insightnews.feature.explore

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.network.model.Category

sealed interface ExploreUiState {
    data object Loading : ExploreUiState

    @Stable
    data class Success(
        val topHeadlines: List<Article> = listOf(),
        val urlToTimeCaption: Map<String, State<String>> = mapOf(),
        val category: State<Category>,
        val categorisedHeadlines: State<List<Article>>,
    ) : ExploreUiState

    data class Error(
        @DrawableRes val errorIconId: Int,
        @StringRes val message: Int,
    ) : ExploreUiState
}