package com.github.artnehay.insightnews.core.data.util

import com.github.artnehay.insightnews.core.network.model.NetworkArticle

fun NetworkArticle.isEmpty() =
    listOf(title, url, urlToImage).any(String?::isNullOrRemoved)

fun String?.isNullOrRemoved() = this == null || this == "[REMOVED]"
