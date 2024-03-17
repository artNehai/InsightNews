package com.github.artnehay.insightnews.core.data.util

import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.model.Source
import com.github.artnehay.insightnews.core.model.SourceHeader
import com.github.artnehay.insightnews.core.network.model.NetworkArticle
import com.github.artnehay.insightnews.core.network.model.NetworkSource
import com.github.artnehay.insightnews.core.network.model.NetworkSourceHeader

fun NetworkArticle.toArticle() = Article(
    source = this.networkSourceHeader.toSourceHeader(),
    author = this.author ?: "",
    title = this.title ?: "",
    description = this.description ?: "",
    url = this.url ?: "",
    urlToImage = this.urlToImage ?: "",
    publishedAt = this.publishedAt ?: "",
    content = this.content ?: "",
)

fun NetworkSource.toSource() = Source(
    id = this.id ?: "",
    name = this.name ?: "",
    description = this.description ?: "",
    url = this.url ?: "",
    category = this.category ?: "",
    language = this.language ?: "",
    country = this.country ?: "",
)

fun NetworkSourceHeader.toSourceHeader() = SourceHeader(
    id = this.id ?: "",
    name = this.name ?: "",
)

