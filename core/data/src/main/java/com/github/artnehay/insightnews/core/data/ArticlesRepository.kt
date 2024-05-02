package com.github.artnehay.insightnews.core.data

import android.util.Log
import com.github.artnehay.insightnews.core.data.di.NewsApiDataSource
import com.github.artnehay.insightnews.core.data.util.isEmpty
import com.github.artnehay.insightnews.core.data.util.toArticle
import com.github.artnehay.insightnews.core.data.util.toArticleEntity
import com.github.artnehay.insightnews.core.database.ArticleEntity
import com.github.artnehay.insightnews.core.database.NewsDatabase
import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.network.NewsRemoteDataSource
import com.github.artnehay.insightnews.core.network.model.Category
import com.github.artnehay.insightnews.core.network.model.Category.All
import com.github.artnehay.insightnews.core.network.model.NetworkArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticlesRepository @Inject constructor(
    @NewsApiDataSource
    private val newsApiRemoteDataSource: NewsRemoteDataSource,
    private val newsDatabase: NewsDatabase,
) : IArticlesRepository {

    override suspend fun getTopHeadlines(): List<Article> =
        newsApiRemoteDataSource
            .getTopHeadlines()
            .filterNot(NetworkArticle::isEmpty)
            .map(NetworkArticle::toArticle)

    override suspend fun getHeadlinesInCategory(category: Category): List<Article> {
        return if (category == All) {
            newsApiRemoteDataSource
                .getAllArticles()
                .filterNot(NetworkArticle::isEmpty)
                .map(NetworkArticle::toArticle)
        } else {
            newsApiRemoteDataSource
                .getHeadlinesInCategory(category.urlPath)
                .filterNot(NetworkArticle::isEmpty)
                .map(NetworkArticle::toArticle)
        }
    }

    override suspend fun saveToDatabase(article: Article): Boolean =
        tryAccessDatabase {
            newsDatabase.articleDao().insert(article.toArticleEntity())
        }

    override suspend fun removeFromDatabase(article: Article): Boolean =
        tryAccessDatabase {
            newsDatabase.articleDao().delete(article.toArticleEntity())
        }

    override fun getSavedArticles(): Flow<List<Article>> =
        newsDatabase.articleDao().getAll()
            .map { list -> list.map(ArticleEntity::toArticle) }

    private suspend fun tryAccessDatabase(
        action: suspend () -> Unit,
    ): Boolean =
        try {
            action()
            true
        } catch (e: IOException) {
            Log.e("ArticlesRepository", e.stackTraceToString())
            false
        }
}