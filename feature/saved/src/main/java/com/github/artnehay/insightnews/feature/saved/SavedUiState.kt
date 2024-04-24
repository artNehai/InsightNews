package com.github.artnehay.insightnews.feature.saved

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.artnehay.insightnews.core.model.Article

sealed interface SavedUiState {
    data object Loading : SavedUiState

    data class Success(
        val savedArticles: List<Article> = listOf(),
        val urlToTimeCaption: Map<String, String> = mapOf(),
    ) : SavedUiState

    data class Error(
        @DrawableRes val errorIconId: Int,
        @StringRes val message: Int,
    ) : SavedUiState
}