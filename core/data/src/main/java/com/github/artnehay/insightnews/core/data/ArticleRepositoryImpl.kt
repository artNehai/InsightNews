package com.github.artnehay.insightnews.core.data

import android.util.Log
import com.github.artnehay.insightnews.core.data.mapper.toArticle
import com.github.artnehay.insightnews.core.data.mapper.toArticleEntity
import com.github.artnehay.insightnews.core.data.mapper.toCategoryDto
import com.github.artnehay.insightnews.core.data.util.isEmpty
import com.github.artnehay.insightnews.core.database.ArticleEntity
import com.github.artnehay.insightnews.core.database.NewsDatabase
import com.github.artnehay.insightnews.core.domain.model.Article
import com.github.artnehay.insightnews.core.domain.model.Category
import com.github.artnehay.insightnews.core.domain.repository.ArticleRepository
import com.github.artnehay.insightnews.core.network.ArticleRemoteDataSource
import com.github.artnehay.insightnews.core.network.model.ArticleDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepositoryImpl @Inject constructor(
    private val articleRemoteDataSource: ArticleRemoteDataSource,
    private val newsDatabase: NewsDatabase,
) : ArticleRepository {

    override suspend fun getTopHeadlines(): List<Article> =
        articleRemoteDataSource
            .getTopHeadlines()
            .parseNetworkArticles()

    override suspend fun getHeadlinesInCategory(category: Category): List<Article> {
        val networkArticles =
            if (category == Category.All) {
                articleRemoteDataSource.getAllArticles()
            } else {
                articleRemoteDataSource.getHeadlinesInCategory(category.toCategoryDto())
            }
        return networkArticles.parseNetworkArticles()
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

    private fun List<ArticleDto>.parseNetworkArticles(): List<Article> =
        this.filterNot(ArticleDto::isEmpty)
            .distinctBy { it.title }
            .map(ArticleDto::toArticle)

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