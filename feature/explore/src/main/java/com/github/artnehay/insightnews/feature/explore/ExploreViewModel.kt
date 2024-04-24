package com.github.artnehay.insightnews.feature.explore

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.artnehay.insightnews.core.data.ArticlesRepository
import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.network.util.NewsApiException
import com.github.artnehay.insightnews.core.ui.R.drawable.cloud_off_icon
import com.github.artnehay.insightnews.core.ui.R.drawable.wifi_off_icon
import com.github.artnehay.insightnews.core.ui.R.string.internet_connection_error
import com.github.artnehay.insightnews.core.ui.R.string.remote_api_error
import com.github.artnehay.insightnews.core.ui.util.getUrlToTimeCaptionMap
import com.github.artnehay.insightnews.feature.explore.ExploreUiState.Error
import com.github.artnehay.insightnews.feature.explore.ExploreUiState.Loading
import com.github.artnehay.insightnews.feature.explore.ExploreUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val articlesRepository: ArticlesRepository,
) : ViewModel() {

    var exploreUiState by mutableStateOf<ExploreUiState>(Loading)
        private set

    private lateinit var savedArticles: StateFlow<List<Article>>

    init {
        viewModelScope.launch {
            savedArticles = articlesRepository.getSavedArticles().stateIn(viewModelScope)
            fetchTopHeadlines()
        }
    }

    fun fetchTopHeadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            exploreUiState = try {
                val newHeadlines: List<Article> =
                    articlesRepository.getTopHeadlines().onEach { headline ->
                        if (headline.isSavedToDb()) {
                            ArticleToIsInDatabasePropertyMap.setValue(
                                article = headline,
                                value = true,
                            )
                        }
                    }
                Success(
                    topHeadlines = newHeadlines,
                    urlToTimeCaption = newHeadlines.getUrlToTimeCaptionMap(),
                )
            } catch (apiE: NewsApiException) {
                Log.e(
                    /*tag*/ "ExploreViewModel",
                    /*msg*/ "Error ${apiE.code} - ${apiE.message}",
                )
                Error(
                    errorIconId = cloud_off_icon,
                    message = remote_api_error,
                )
            } catch (ioE: IOException) {
                Log.e(
                    /*tag*/ "ExploreViewModel",
                    /*msg*/ ioE.message ?: "IOException when connecting to a remote data source",
                )
                Error(
                    errorIconId = wifi_off_icon,
                    message = internet_connection_error,
                )
            }
        }
    }

    fun saveToDatabase(article: Article) {
        viewModelScope.launch {
            val isSaved = articlesRepository.saveToDatabase(article)
            ArticleToIsInDatabasePropertyMap.setValue(article, isSaved)
        }
    }

    fun removeFromDatabase(article: Article) {
        viewModelScope.launch {
            val isRemoved = articlesRepository.removeFromDatabase(article)
            ArticleToIsInDatabasePropertyMap.setValue(article, !isRemoved)
        }
    }

    override fun onCleared() {
        super.onCleared()
        ArticleToIsInDatabasePropertyMap.clear()
    }

    private fun Article.isSavedToDb(): Boolean =
        this.url in savedArticles.value.map { it.url }
}

private val ArticleToIsInDatabasePropertyMap = mutableMapOf<Article, MutableState<Boolean>>()

val Article.isInDatabase: State<Boolean>
    get() = ArticleToIsInDatabasePropertyMap.getOrPut(this) { mutableStateOf(false) }