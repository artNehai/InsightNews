package com.github.artnehay.insightnews.feature.explore

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.artnehay.insightnews.core.data.ArticlesRepository
import com.github.artnehay.insightnews.core.model.Article
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val articlesRepository: ArticlesRepository,
) : ViewModel() {

    private val _exploreUiState = mutableStateOf(ExploreUiState())
    val exploreUiState: State<ExploreUiState> = _exploreUiState

    private var currentHeadlinesPage = 1

    init {
        fetchNewTopHeadlines()
    }

    fun fetchNewTopHeadlines() {
        viewModelScope.launch {
            val newHeadlines = articlesRepository.getTopHeadlines(currentHeadlinesPage)
            _exploreUiState.value = _exploreUiState.value.complementHeadlines(newHeadlines)
            currentHeadlinesPage++
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