package com.github.artnehay.insightnews.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.github.artnehay.insightnews.InsightNewsApplication
import com.github.artnehay.insightnews.feature.explore.ExploreViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val application = this[APPLICATION_KEY] as InsightNewsApplication
            ExploreViewModel(application.container.articlesRepository)
        }
    }
}