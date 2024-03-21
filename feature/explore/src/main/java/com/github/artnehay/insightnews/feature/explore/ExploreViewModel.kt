package com.github.artnehay.insightnews.feature.explore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.artnehay.insightnews.core.data.ArticlesRepository
import com.github.artnehay.insightnews.core.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val articlesRepository: ArticlesRepository,
) : ViewModel() {

    var exploreUiState by mutableStateOf(ExploreUiState())
        private set

    init {
        fetchTopHeadlines()
    }

    private fun fetchTopHeadlines() {
        viewModelScope.launch {
            val newHeadlines = articlesRepository.getTopHeadlines()
            exploreUiState = exploreUiState.complementHeadlines(newHeadlines)
        }
    }
}

data class ExploreUiState(
    val topHeadlines: List<Article> = listOf(),
)

private fun ExploreUiState.complementHeadlines(newHeadlines: List<Article>) =
    this.copy(
        topHeadlines = this.topHeadlines + newHeadlines
    )