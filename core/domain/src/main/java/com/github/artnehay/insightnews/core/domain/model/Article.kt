package com.github.artnehay.insightnews.core.domain.model

data class Article(
    val source: SourceHeader,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val timeToReadMin: Int,
    val content: String,
)