package com.github.artnehay.insightnews.feature.explore.fake

import com.github.artnehay.insightnews.core.data.IArticlesRepository
import com.github.artnehay.insightnews.core.data.fake.FakeArticle
import com.github.artnehay.insightnews.core.model.Article
import kotlinx.coroutines.flow.flow

object FakeArticlesRepository : IArticlesRepository {
    override suspend fun getTopHeadlines() = listOf(FakeArticle)

    override suspend fun saveToDatabase(article: Article) = true

    override suspend fun removeFromDatabase(article: Article) = true

    override fun getSavedArticles() = flow { emit(listOf(FakeArticle)) }
}