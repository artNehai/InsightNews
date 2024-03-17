package com.github.artnehay.insightnews

import android.app.Application
import com.github.artnehay.insightnews.core.data.AppContainer

class InsightNewsApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}