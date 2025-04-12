package com.github.artnehay.insightnews.core.data

import android.content.Context
import androidx.room.Room
import com.github.artnehay.insightnews.core.data.fake.FakeArticleRemoteDataSource
import com.github.artnehay.insightnews.core.database.NewsDatabase
import com.github.artnehay.insightnews.core.domain.repository.ArticleRepository
import com.github.artnehay.insightnews.core.testing.fake.FakeArticle1
import com.github.artnehay.insightnews.core.testing.fake.FakeArticle2
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
class ArticlesRepositoryTest {

    private lateinit var newsDatabase: NewsDatabase
    private lateinit var articleRepository: ArticleRepository

    @Before
    fun createArticlesRepository() {
        val context: Context = RuntimeEnvironment.getApplication()
        newsDatabase = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        articleRepository = ArticleRepositoryImpl(
            articleRemoteDataSource = FakeArticleRemoteDataSource,
            newsDatabase = newsDatabase,
        )
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        newsDatabase.close()
    }

    @Test
    fun getTopHeadlines() = runTest {
        articleRepository.getTopHeadlines() shouldBe listOf(FakeArticle1, FakeArticle2)
    }

    @Test
    @Throws(IOException::class)
    fun getSavedArticles() = runTest {
        articleRepository.saveToDatabase(FakeArticle1)
        articleRepository.saveToDatabase(FakeArticle2)
        articleRepository.getSavedArticles().first() shouldBe listOf(FakeArticle1, FakeArticle2)
    }
}