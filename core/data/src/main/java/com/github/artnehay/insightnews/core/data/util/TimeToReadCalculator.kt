package com.github.artnehay.insightnews.core.data.util

fun String.calcTimeToRead(): Int? {
    val chars = this
        .substringAfter("[+")
        .substringBefore(" chars")
        .toIntOrNull() ?: return null
    return chars / 1000 + 1
}