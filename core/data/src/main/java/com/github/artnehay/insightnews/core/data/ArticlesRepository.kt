package com.github.artnehay.insightnews.core.data

import com.github.artnehay.insightnews.core.data.util.toArticle
import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.network.NewsApiService
import com.github.artnehay.insightnews.core.network.model.NetworkArticle

class ArticlesRepository(
    private val newsApiService: NewsApiService,
) {
    suspend fun getTopHeadlines(page: Int = 1): List<Article> =
        newsApiService
            .getTopHeadlines(page)
            .networkArticles
            .map(NetworkArticle::toArticle)
}