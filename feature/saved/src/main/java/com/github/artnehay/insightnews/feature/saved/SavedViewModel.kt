package com.github.artnehay.insightnews.feature.saved

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.artnehay.insightnews.core.data.ArticlesRepository
import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.ui.R
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

    var savedUiState: StateFlow<SavedUiState> =
        try {
            articlesRepository.getSavedArticles().map { Success(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
                    initialValue = Loading,
                )
        } catch (ioE: IOException) {
            Log.e(
                /*tag*/ "SavedViewModel",
                /*msg*/ ioE.message ?: "IOException when connecting to the news database",
            )
            MutableStateFlow(
                Error(
                    errorIconId = R.drawable.database_icon,
                    message = R.string.internet_connection_error,
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