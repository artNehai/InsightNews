package com.github.artnehay.insightnews.core.ui.util

import android.text.format.DateUtils
import com.github.artnehay.insightnews.core.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

suspend fun List<Article>.getUrlToTimeCaptionMap(): Map<String, String> {
    val map = mutableMapOf<String, String>()
    withContext(Dispatchers.Default) {
        forEach { article ->
            map[article.url] = article.getTimeCaption()
        }
    }
    return map
}

fun Article.getTimeCaption(): String {
    val currentDate = Date()
    val published: Date =
        SimpleDateFormat.getDateTimeInstance().parse(publishedAt)
            ?: return "$timeToReadMin min read"

    val difference: Long = currentDate.time - published.time
    val seconds = TimeUnit.MILLISECONDS.toSeconds(difference)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(difference)
    val hours = TimeUnit.MILLISECONDS.toHours(difference)
    val days = TimeUnit.MILLISECONDS.toDays(difference)

    val publishedAgo = when {
        seconds <= 60 -> "1 minute ago"
        minutes < 60 -> "$minutes minutes ago"
        hours == 1L -> "1 hour ago"
        hours <= 6 -> "$hours hours ago"
        DateUtils.isToday(published.time) -> "Today"
        days < 2L -> "Yesterday"
        days <= 7 -> "This week"
        else -> SimpleDateFormat.getDateInstance().format(published)
    }

    return "$publishedAgo | $timeToReadMin min read"
}