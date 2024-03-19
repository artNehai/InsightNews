package com.github.artnehay.insightnews

import android.app.Application
import com.github.artnehay.insightnews.core.data.ArticlesRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class InsightNewsApplication : Application() {

    @Inject
    lateinit var articlesRepository: ArticlesRepository
}