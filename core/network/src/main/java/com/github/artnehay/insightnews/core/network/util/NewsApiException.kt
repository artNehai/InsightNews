package com.github.artnehay.insightnews.core.network.util

class NewsApiException(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(message, cause)