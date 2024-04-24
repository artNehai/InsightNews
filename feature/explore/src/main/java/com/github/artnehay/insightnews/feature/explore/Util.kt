package com.github.artnehay.insightnews.feature.explore

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.github.artnehay.insightnews.core.model.Article

fun MutableMap<Article, MutableState<Boolean>>.setValue(
    article: Article,
    value: Boolean,
) {
    this.getOrPut(article) { mutableStateOf(value) }.value = value
}