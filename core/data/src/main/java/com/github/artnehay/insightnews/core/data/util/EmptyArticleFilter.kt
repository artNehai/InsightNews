package com.github.artnehay.insightnews.core.data.util

import com.github.artnehay.insightnews.core.network.model.ArticleDto

fun ArticleDto.isEmpty() =
    listOf(sourceHeaderDto.name, title, url, urlToImage).any(String?::isNullOrRemoved)

fun String?.isNullOrRemoved() = this == null || this == "[REMOVED]"
