package com.github.artnehay.insightnews.core.data.util

import com.github.artnehay.insightnews.core.database.ArticleEntity
import com.github.artnehay.insightnews.core.model.Article

fun Article.toArticleEntity() = ArticleEntity(
    sourceId = source.id,
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content,
)