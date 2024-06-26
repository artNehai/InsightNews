package com.github.artnehay.insightnews.core.data.fake

import com.github.artnehay.insightnews.core.data.IArticlesRepository
import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.network.model.Category
import com.github.artnehay.insightnews.core.testing.fake.FakeArticle1
import com.github.artnehay.insightnews.core.testing.fake.FakeArticle2
import kotlinx.coroutines.flow.flow

object FakeArticlesRepository : IArticlesRepository {

    override suspend fun getTopHeadlines() = listOf(FakeArticle1)

    override suspend fun getHeadlinesInCategory(category: Category) = listOf(FakeArticle2)

    override suspend fun saveToDatabase(article: Article) = true

    override suspend fun removeFromDatabase(article: Article) = true

    override fun getSavedArticles() = flow { emit(listOf(FakeArticle1)) }


}