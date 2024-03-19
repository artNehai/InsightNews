package com.github.artnehay.insightnews.core.data

import com.github.artnehay.insightnews.core.data.util.toArticle
import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.network.NewsRemoteDataSource
import com.github.artnehay.insightnews.core.network.model.NetworkArticle
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticlesRepository @Inject constructor(
    private val retrofitDataSource: NewsRemoteDataSource,
) {
    suspend fun getTopHeadlines(): List<Article> =
        retrofitDataSource
            .getTopHeadlines()
            .networkArticles
            .map(NetworkArticle::toArticle)
}