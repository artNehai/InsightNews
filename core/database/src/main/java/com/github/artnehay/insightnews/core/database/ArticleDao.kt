package com.github.artnehay.insightnews.core.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: ArticleEntity)

    @Delete
    suspend fun delete(article: ArticleEntity)

    @Query("SELECT * FROM article")
    fun getAll(): Flow<List<ArticleEntity>>

    @Query("SELECT COUNT(*) FROM article WHERE url = :url")
    suspend fun getArticleCount(url: String): Int
}