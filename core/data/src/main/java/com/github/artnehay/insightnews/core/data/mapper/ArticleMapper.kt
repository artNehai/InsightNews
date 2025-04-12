package com.github.artnehay.insightnews.core.data.mapper

import com.github.artnehay.insightnews.core.data.util.calcTimeToRead
import com.github.artnehay.insightnews.core.data.util.toLocalDateTime
import com.github.artnehay.insightnews.core.database.ArticleEntity
import com.github.artnehay.insightnews.core.domain.model.Article
import com.github.artnehay.insightnews.core.domain.model.SourceHeader
import com.github.artnehay.insightnews.core.network.model.ArticleDto

fun ArticleDto.toArticle() = Article(
    source = this.sourceHeaderDto.toSourceHeader(),
    author = this.author ?: "",
    title = this.title ?: "",
    description = this.description ?: "",
    url = this.url ?: "",
    urlToImage = this.urlToImage ?: "",
    publishedAt = this.publishedAt.toLocalDateTime(),
    timeToReadMin = this.content?.calcTimeToRead() ?: 0,
    content = this.content ?: "",
)

fun Article.toArticleEntity() = ArticleEntity(
    sourceId = source.id,
    sourceName = source.name,
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    timeToReadMin = timeToReadMin,
    content = content,
)

fun ArticleEntity.toArticle() = Article(
    source = SourceHeader(id = sourceId, name = sourceName),
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    timeToReadMin = timeToReadMin,
    content = content,
)