package com.github.artnehay.insightnews.feature.explore

import com.github.artnehay.insightnews.core.data.fake.FakeArticlesRepository
import com.github.artnehay.insightnews.core.testing.fake.FakeArticle1
import com.github.artnehay.insightnews.core.testing.fake.TestDispatcherRule
import com.github.artnehay.insightnews.feature.explore.ExploreUiState.Loading
import com.github.artnehay.insightnews.feature.explore.ExploreUiState.Success
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExploreViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()
    private lateinit var exploreViewModel: ExploreViewModel

    @Before
    fun initExploreViewModel() {
        exploreViewModel = ExploreViewModel(FakeArticlesRepository)
    }

    @Test
    fun verifyInitialUiStateLoading() {
        runBlocking {
            exploreViewModel.exploreUiState shouldBe Loading
        }
    }

    @Test
    fun verifyFetchingTopHeadlinesAtInitialization() {
        runBlocking {
            delay(100)
            exploreViewModel.exploreUiState shouldBe Success(
                topHeadlines = listOf(FakeArticle1),
                urlToTimeCaption = mapOf(FakeArticle1.url to "Mar 12, 2023 | 0 min read")
            )
        }
    }
}