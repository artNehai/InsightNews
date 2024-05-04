package com.github.artnehay.insightnews.feature.explore

import com.github.artnehay.insightnews.core.data.fake.FakeArticlesRepository
import com.github.artnehay.insightnews.core.network.model.Category.All
import com.github.artnehay.insightnews.core.testing.fake.FakeArticle1
import com.github.artnehay.insightnews.core.testing.fake.FakeArticle2
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
            delay(500)
            val resultUiState = exploreViewModel.exploreUiState as Success
            resultUiState.topHeadlines shouldBe listOf(FakeArticle1)
            resultUiState.urlToTimeCaption.mapValues { it.value.value } shouldBe
                    mapOf(
                        FakeArticle1.url to "Mar 12, 2023 | 0 min read",
                        FakeArticle2.url to "Jun 1, 1905 | 0 min read",
                    )
            resultUiState.category.value shouldBe All
            resultUiState.categorisedHeadlines.value shouldBe listOf(FakeArticle2)
        }
    }
}