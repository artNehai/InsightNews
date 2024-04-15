package com.github.artnehay.insightnews.feature.explore

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import com.github.artnehay.insightnews.core.ui.util.getTimeCaption
import com.github.artnehay.insightnews.feature.explore.ExploreUiState.Error
import com.github.artnehay.insightnews.feature.explore.ExploreUiState.Loading
import com.github.artnehay.insightnews.feature.explore.ExploreUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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
        viewModelScope.launch(Dispatchers.IO) {
            exploreUiState = try {
                val newHeadlines = articlesRepository.getTopHeadlines()

                val urlToTimeCaption = async(Dispatchers.Default) {
                    val map = mutableMapOf<String, String>()
                    newHeadlines.forEach {
                        map[it.url] = it.getTimeCaption()
                    }
                    map
                }

                Success(
                    topHeadlines = newHeadlines,
                    urlToTimeCaption = urlToTimeCaption.await(),
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
            articlesRepository.saveToDatabase(article)
        }
        viewModelScope.launch {
            val uiState = exploreUiState as Success
            val headlines = uiState.topHeadlines.toMutableList()
            headlines.set(
                index = headlines.indexOf(article),
                element = article.copy(isSavedToDb = true),
            )
            exploreUiState = uiState.copy(
                topHeadlines = headlines
            )
        }
    }

    fun removeFromDatabase(article: Article) {
        val uiState = exploreUiState as Success
        val headlines = uiState.topHeadlines.toMutableList()
        headlines.set(
            index = headlines.indexOf(article),
            element = article.copy(isSavedToDb = false),
        )
        exploreUiState = uiState.copy(
            topHeadlines = headlines
        )
    }
}

sealed interface ExploreUiState {
    data object Loading : ExploreUiState

    data class Success(
        val topHeadlines: List<Article> = listOf(),
        val urlToTimeCaption: Map<String, String> = mapOf(),
    ) : ExploreUiState

    data class Error(
        @DrawableRes val errorIconId: Int,
        @StringRes val message: Int,
    ) : ExploreUiState
}