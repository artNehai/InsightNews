package com.github.artnehay.insightnews.core.network

import com.github.artnehay.insightnews.core.network.model.ArticleDto
import com.github.artnehay.insightnews.core.network.model.CategoryDto

interface ArticleRemoteDataSource {
    suspend fun getTopHeadlines(): List<ArticleDto>
    suspend fun getHeadlinesInCategory(categoryDto: CategoryDto): List<ArticleDto>
    suspend fun getAllArticles(): List<ArticleDto>
}