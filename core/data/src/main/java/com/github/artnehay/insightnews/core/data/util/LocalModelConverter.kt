package com.github.artnehay.insightnews.core.data.util

import com.github.artnehay.insightnews.core.database.ArticleEntity
import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.model.SourceHeader

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

fun ArticleEntity.toArticle() = Article(
    source = SourceHeader(id = sourceId, name = ""),
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    isSavedToDb = true,
    content = content,
)