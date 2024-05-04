package com.github.artnehay.insightnews.feature.explore

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.artnehay.insightnews.core.data.IArticlesRepository
import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.network.model.Category
import com.github.artnehay.insightnews.core.network.model.Category.All
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
    private val articlesRepository: IArticlesRepository,
) : ViewModel() {

    var exploreUiState by mutableStateOf<ExploreUiState>(Loading)
        private set

    private lateinit var savedArticles: StateFlow<List<Article>>
    private val chosenCategory = mutableStateOf(All)
    private val categorisedHeadlinesCache = mutableMapOf<Category, List<Article>>()
    private val currentCategorisedHeadlines = mutableStateOf(listOf<Article>())

    @Stable
    private val urlToTimeCaptionMap = mutableMapOf<String, MutableState<String>>()

    init {
        viewModelScope.launch {
            savedArticles = articlesRepository.getSavedArticles().stateIn(viewModelScope)
            fetchTopHeadlines()
            fetchHeadlinesInCategory(chosenCategory.value)
        }
    }

    fun fetchTopHeadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            tryFetchingFromRemote {
                val newHeadlines: List<Article> =
                    articlesRepository.getTopHeadlines().onEach { headline ->
                        if (headline.isSavedToDb()) {
                            ArticleToIsInDatabasePropertyMap.setValue(headline, true)
                        }
                    }
                newHeadlines.getUrlToTimeCaptionMap().forEach {
                    urlToTimeCaptionMap.setValue(it.key, it.value)
                }
                exploreUiState = Success(
                    topHeadlines = newHeadlines,
                    urlToTimeCaption = urlToTimeCaptionMap,
                    category = chosenCategory,
                    categorisedHeadlines = currentCategorisedHeadlines
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

    fun selectCategory(category: Category) {
        if (category in categorisedHeadlinesCache) {
            currentCategorisedHeadlines.value = categorisedHeadlinesCache[category] ?: listOf()
        } else {
            fetchHeadlinesInCategory(category)
        }
        chosenCategory.value = category
    }

    override fun onCleared() {
        super.onCleared()
        ArticleToIsInDatabasePropertyMap.clear()
    }

    private fun fetchHeadlinesInCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            tryFetchingFromRemote {
                val headlines = articlesRepository.getHeadlinesInCategory(category)
                headlines.getUrlToTimeCaptionMap().forEach {
                    urlToTimeCaptionMap.setValue(it.key, it.value)
                }
                currentCategorisedHeadlines.value = categorisedHeadlinesCache.getOrPut(category) {
                    headlines
                }
            }
        }
    }

    private fun Article.isSavedToDb(): Boolean =
        this.url in savedArticles.value.map { it.url }

    private suspend fun tryFetchingFromRemote(actions: suspend () -> Unit) {
        try {
            actions()
        } catch (apiE: NewsApiException) {
            Log.e(
                /*tag*/ "ExploreViewModel",
                /*msg*/ "Error ${apiE.code} - ${apiE.message}",
            )
            exploreUiState = Error(
                errorIconId = cloud_off_icon,
                message = remote_api_error,
            )
        } catch (ioE: IOException) {
            Log.e(
                /*tag*/ "ExploreViewModel",
                /*msg*/ ioE.message ?: "IOException when connecting to a remote data source",
            )
            exploreUiState = Error(
                errorIconId = wifi_off_icon,
                message = internet_connection_error,
            )
        }
    }
}

private val ArticleToIsInDatabasePropertyMap = mutableMapOf<Article, MutableState<Boolean>>()

val Article.isInDatabase: State<Boolean>
    get() = ArticleToIsInDatabasePropertyMap.getOrPut(this) { mutableStateOf(false) }