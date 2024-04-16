package com.github.artnehay.insightnews.feature.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.artnehay.insightnews.core.ui.ArticleCard
import com.github.artnehay.insightnews.core.ui.ErrorScreen
import com.github.artnehay.insightnews.core.ui.NoticeIcon
import com.github.artnehay.insightnews.core.ui.R.dimen.medium_content_spacer
import com.github.artnehay.insightnews.core.ui.theme.InsightNewsTheme
import com.github.artnehay.insightnews.feature.saved.SavedUiState.Error
import com.github.artnehay.insightnews.feature.saved.SavedUiState.Loading
import com.github.artnehay.insightnews.feature.saved.SavedUiState.Success

@Composable
fun SavedScreen(
    modifier: Modifier = Modifier,
    viewModel: SavedViewModel = hiltViewModel(),
) {
    val uiState by viewModel.savedUiState.collectAsState()
    when (uiState) {
        is Loading -> {}

        is Success -> ResultScreen(
            viewModel = viewModel,
            modifier = modifier,
        )

        is Error -> {
            val errorUiState = uiState as Error
            ErrorScreen(
                iconId = errorUiState.errorIconId,
                messageId = errorUiState.message,
                onRetryClick = { },
                modifier = modifier,
            )
        }
    }
}

@Composable
fun ResultScreen(
    viewModel: SavedViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState = viewModel.savedUiState.collectAsState().value as Success

    if (uiState.savedArticles.isEmpty()) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                NoticeIcon(iconId = R.drawable.book_icon)

                Spacer(Modifier.height(dimensionResource(medium_content_spacer)))

                Text(
                    text = stringResource(R.string.empty_bookmarks_screen_notice),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(
                items = uiState.savedArticles,
                key = { it.url },
            ) { article ->
                ArticleCard(
                    article = article,
                    modifier = Modifier.padding(vertical = dimensionResource(medium_content_spacer)),
                    timeCaption = uiState.urlToTimeCaption[article.url] ?: "",
                )
                if (article != uiState.savedArticles.last()) {
                    HorizontalDivider()
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SavedArticlesScreenPreview() {
    InsightNewsTheme {
        SavedScreen()
    }
}