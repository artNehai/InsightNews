package com.github.artnehay.insightnews.core.domain.repository

import com.github.artnehay.insightnews.core.domain.model.Article
import java.util.concurrent.Flow

interface ArticleRepository {
    suspend fun getTopHeadlines(): List<Article>

    suspend fun getHeadlinesInCategory(category: Category): List<Article>

    suspend fun saveToDatabase(article: Article): Boolean

    suspend fun removeFromDatabase(article: Article): Boolean

    fun getSavedArticles(): Flow<List<Article>>
}