package com.github.artnehay.insightnews.core.data

import com.github.artnehay.insightnews.core.network.newsApiService

class AppContainer {
    val articlesRepository: ArticlesRepository by lazy {
        ArticlesRepository(newsApiService)
    }
}