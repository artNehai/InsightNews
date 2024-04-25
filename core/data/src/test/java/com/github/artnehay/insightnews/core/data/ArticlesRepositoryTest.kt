package com.github.artnehay.insightnews.core.data

import android.content.Context
import androidx.room.Room
import com.github.artnehay.insightnews.core.data.fake.FakeArticle1
import com.github.artnehay.insightnews.core.data.fake.FakeArticle2
import com.github.artnehay.insightnews.core.data.fake.FakeNewsRemoteDataSource
import com.github.artnehay.insightnews.core.database.NewsDatabase
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
    private lateinit var articlesRepository: ArticlesRepository

    @Before
    fun createDb() {
        val context: Context = RuntimeEnvironment.getApplication()
        newsDatabase = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        articlesRepository = ArticlesRepository(
            newsApiRemoteDataSource = FakeNewsRemoteDataSource,
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
        articlesRepository.getTopHeadlines() shouldBe listOf(FakeArticle1, FakeArticle2)
    }

    @Test
    @Throws(IOException::class)
    fun getSavedArticles() = runTest {
        articlesRepository.saveToDatabase(FakeArticle1)
        articlesRepository.saveToDatabase(FakeArticle2)
        articlesRepository.getSavedArticles().first() shouldBe listOf(FakeArticle1, FakeArticle2)
    }
}