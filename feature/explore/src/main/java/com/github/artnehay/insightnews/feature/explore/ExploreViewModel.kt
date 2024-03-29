package com.github.artnehay.insightnews.feature.explore

import androidx.annotation.DrawableRes
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
        viewModelScope.launch {
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
                Error(
                    errorIconId = cloud_off_icon,
                    message = apiE.message ?: "",
                )
            } catch (ioe: IOException) {
                Error(
                    errorIconId = wifi_off_icon,
                    message = ioe.message ?: "",
                )
            }
        }
    }
}

sealed interface ExploreUiState {
    data object Loading : ExploreUiState

    data class Success(
        val topHeadlines: List<Article> = listOf(),
        val urlToTimeCaption: Map<String, String>,
    ) : ExploreUiState

    data class Error(
        @DrawableRes val errorIconId: Int,
        val message: String,
    ) : ExploreUiState
}