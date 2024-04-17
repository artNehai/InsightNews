package com.github.artnehay.insightnews.feature.saved

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.artnehay.insightnews.core.data.ArticlesRepository
import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.ui.R
import com.github.artnehay.insightnews.core.ui.util.getUrlToTimeCaptionMap
import com.github.artnehay.insightnews.feature.saved.SavedUiState.Error
import com.github.artnehay.insightnews.feature.saved.SavedUiState.Loading
import com.github.artnehay.insightnews.feature.saved.SavedUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    articlesRepository: ArticlesRepository,
) : ViewModel() {

    val savedUiState: StateFlow<SavedUiState> =
        try {
            articlesRepository.getSavedArticles().map { articles ->
                Success(
                    savedArticles = articles,
                    urlToTimeCaption = articles.getUrlToTimeCaptionMap(),
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
                initialValue = Loading,
            )
        } catch (e: IOException) {
            Log.e(
                /*tag*/
                "SavedViewModel",
                /*msg*/
                e.message
                    ?: "${e.javaClass.simpleName} exception occurred when connecting to the news database",
            )
            MutableStateFlow(
                Error(
                    errorIconId = R.drawable.database_icon,
                    message = R.string.database_connection_error,
                )
            )
        }
}

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