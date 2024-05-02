package com.github.artnehay.insightnews.feature.explore

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

fun <K, V> MutableMap<K, MutableState<V>>.setValue(
    key: K,
    value: V,
) {
    this.getOrPut(key) { mutableStateOf(value) }.value = value
}