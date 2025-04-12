package com.github.artnehay.insightnews.core.database.di

import android.content.Context
import com.github.artnehay.insightnews.core.database.NewsDatabase
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideNewsDatabase(
        @ApplicationContext context: Context,
    ) = NewsDatabase.getDatabase(context)
}