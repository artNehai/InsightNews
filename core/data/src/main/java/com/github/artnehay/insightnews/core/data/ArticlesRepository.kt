package com.github.artnehay.insightnews.core.data

import com.github.artnehay.insightnews.core.data.di.NewsApiDataSource
import com.github.artnehay.insightnews.core.data.util.isEmpty
import com.github.artnehay.insightnews.core.data.util.toArticle
import com.github.artnehay.insightnews.core.data.util.toArticleEntity
import com.github.artnehay.insightnews.core.database.ArticleEntity
import com.github.artnehay.insightnews.core.database.NewsDatabase
import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.network.NewsRemoteDataSource
import com.github.artnehay.insightnews.core.network.model.NetworkArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticlesRepository @Inject constructor(
    @NewsApiDataSource
    private val newsApiRemoteDataSource: NewsRemoteDataSource,
    private val newsDatabase: NewsDatabase,
) {
    suspend fun getTopHeadlines(): List<Article> =
        newsApiRemoteDataSource
            .getTopHeadlines()
            .filterNot(NetworkArticle::isEmpty)
            .map { it.toArticle(it.isInDatabase()) }

    suspend fun saveToDatabase(article: Article) {
        newsDatabase.articleDao().insert(article.toArticleEntity())
    }

    fun getSavedArticles(): Flow<List<Article>> =
        newsDatabase.articleDao().getAll()
            .map { list -> list.map(ArticleEntity::toArticle) }

    private suspend fun NetworkArticle.isInDatabase(): Boolean {
        val articlesInDb = newsDatabase.articleDao().getArticleCount(url ?: "")
        return articlesInDb > 0
    }
}