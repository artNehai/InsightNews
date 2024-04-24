package com.github.artnehay.insightnews.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.artnehay.insightnews.core.database.fake.FakeArticleEntity1
import com.github.artnehay.insightnews.core.database.fake.FakeArticleEntity2
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {

    private lateinit var articleDao: ArticleDao
    private lateinit var newsDatabase: NewsDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        newsDatabase = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        articleDao = newsDatabase.articleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        newsDatabase.close()
    }

    @Test
    @Throws(IOException::class)
    fun insertArticlesIntoDb() {
        runBlocking {
            articleDao.insert(FakeArticleEntity1)
            articleDao.insert(FakeArticleEntity2)
            articleDao.getAll().first().size shouldBe 2
        }
    }

    @Test
    @Throws(IOException::class)
    fun insertArticleTwice_conflictIgnored() {
        runBlocking {
            articleDao.insert(FakeArticleEntity1)
            val response = articleDao.insert(FakeArticleEntity1)
            articleDao.getAll().first().size shouldBe 1
            response shouldBe -1
        }
    }

    @Test
    @Throws(IOException::class)
    fun deleteArticle() {
        runBlocking {
            articleDao.insert(FakeArticleEntity1)
            articleDao.insert(FakeArticleEntity2)
            articleDao.delete(FakeArticleEntity1)
            val articlesInDb = articleDao.getAll().first()
            articlesInDb.size shouldBe 1
            articlesInDb.first() shouldBe FakeArticleEntity2
        }
    }
}