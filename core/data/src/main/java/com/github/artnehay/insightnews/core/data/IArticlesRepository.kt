package com.github.artnehay.insightnews.core.data

import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.network.model.Category
import kotlinx.coroutines.flow.Flow

// Interface to provide the FakeArticlesRepository in tests
interface IArticlesRepository {
    suspend fun getTopHeadlines(): List<Article>

    suspend fun getHeadlinesInCategory(category: Category): List<Article>

    suspend fun saveToDatabase(article: Article): Boolean

    suspend fun removeFromDatabase(article: Article): Boolean

    fun getSavedArticles(): Flow<List<Article>>
}