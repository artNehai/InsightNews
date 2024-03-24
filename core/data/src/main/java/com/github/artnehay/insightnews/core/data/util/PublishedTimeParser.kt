package com.github.artnehay.insightnews.core.data.util

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun String?.toLocalDateTime(): String {
    if (this == null) return ""

    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    parser.timeZone = TimeZone.getTimeZone("UTC")
    val date = parser.parse(this) ?: return ""

    return SimpleDateFormat.getDateTimeInstance().format(date)
}