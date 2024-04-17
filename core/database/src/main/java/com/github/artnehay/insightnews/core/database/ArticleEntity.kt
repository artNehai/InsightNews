package com.github.artnehay.insightnews.core.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleEntity(
    @ColumnInfo(name = "source_id")
    val sourceId: String,
    val author: String,
    val title: String,
    val description: String,
    @PrimaryKey
    val url: String,
    @ColumnInfo(name = "url_to_image")
    val urlToImage: String,
    @ColumnInfo(name = "published_at")
    val publishedAt: String,
    @ColumnInfo(name = "time_to_read_min")
    val timeToReadMin: Int,
    val content: String,
)