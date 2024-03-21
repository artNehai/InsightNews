package com.github.artnehay.insightnews.core.data

import com.github.artnehay.insightnews.core.data.di.NewsApiDataSource
import com.github.artnehay.insightnews.core.data.util.toArticle
import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.network.NewsRemoteDataSource
import com.github.artnehay.insightnews.core.network.model.NetworkArticle
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticlesRepository @Inject constructor(
    @NewsApiDataSource
    private val newsApiRemoteDataSource: NewsRemoteDataSource,
) {
    suspend fun getTopHeadlines(): List<Article> =
        newsApiRemoteDataSource
            .getTopHeadlines()
            .networkArticles
            .map(NetworkArticle::toArticle)
}