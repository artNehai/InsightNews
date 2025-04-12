package com.github.artnehay.insightnews.core.data.fake

import com.github.artnehay.insightnews.core.network.ArticleRemoteDataSource
import com.github.artnehay.insightnews.core.network.model.ArticleDto
import com.github.artnehay.insightnews.core.network.model.CategoryDto

object FakeArticleRemoteDataSource : ArticleRemoteDataSource {
    override suspend fun getTopHeadlines(): List<ArticleDto> =
        listOf(FakeArticleDto1, FakeArticleDto2)

    override suspend fun getHeadlinesInCategory(categoryDto: CategoryDto): List<ArticleDto> =
        listOf(FakeArticleDto1, FakeArticleDto2)

    override suspend fun getAllArticles(): List<ArticleDto> =
        listOf(FakeArticleDto1, FakeArticleDto2)
}