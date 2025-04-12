package com.github.artnehay.insightnews.core.domain.repository

import com.github.artnehay.insightnews.core.domain.model.Article
import com.github.artnehay.insightnews.core.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getTopHeadlines(): List<Article>

    suspend fun getHeadlinesInCategory(category: Category): List<Article>

    suspend fun saveToDatabase(article: Article): Boolean

    suspend fun removeFromDatabase(article: Article): Boolean

    fun getSavedArticles(): Flow<List<Article>>
}