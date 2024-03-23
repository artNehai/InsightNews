package com.github.artnehay.insightnews.feature.explore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.artnehay.insightnews.core.data.ArticlesRepository
import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.feature.explore.ExploreUiState.Error
import com.github.artnehay.insightnews.feature.explore.ExploreUiState.Loading
import com.github.artnehay.insightnews.feature.explore.ExploreUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val articlesRepository: ArticlesRepository,
) : ViewModel() {

    var exploreUiState by mutableStateOf<ExploreUiState>(Loading)
        private set

    init {
        fetchTopHeadlines()
    }

    fun fetchTopHeadlines() {
        viewModelScope.launch {
            exploreUiState = try {
                val newHeadlines = articlesRepository.getTopHeadlines()
                Success(newHeadlines)
            } catch (_: IOException) {
                Error
            }
        }
    }
}

sealed interface ExploreUiState {
    data class Success(
        val topHeadlines: List<Article> = listOf(),
    ) : ExploreUiState

    data object Loading : ExploreUiState

    data object Error : ExploreUiState
}