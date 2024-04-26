package com.github.artnehay.insightnews.feature.explore

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.testing.fake.FakeArticle1
import com.github.artnehay.insightnews.core.ui.ArticleCard
import com.github.artnehay.insightnews.core.ui.ErrorScreen
import com.github.artnehay.insightnews.core.ui.R.dimen.extra_small_icon_size
import com.github.artnehay.insightnews.core.ui.R.dimen.large_content_spacer
import com.github.artnehay.insightnews.core.ui.R.dimen.medium_content_padding
import com.github.artnehay.insightnews.core.ui.R.dimen.medium_content_spacer
import com.github.artnehay.insightnews.core.ui.R.dimen.small_content_spacer
import com.github.artnehay.insightnews.core.ui.R.dimen.small_icon_size
import com.github.artnehay.insightnews.core.ui.R.drawable.save_icon_filled
import com.github.artnehay.insightnews.core.ui.R.drawable.save_icon_outline
import com.github.artnehay.insightnews.core.ui.R.drawable.search_icon
import com.github.artnehay.insightnews.core.ui.R.drawable.unknown_source_favicon
import com.github.artnehay.insightnews.core.ui.theme.InsightNewsTheme
import com.github.artnehay.insightnews.core.ui.util.SourceToFaviconMap
import com.github.artnehay.insightnews.feature.explore.ExploreUiState.Error
import com.github.artnehay.insightnews.feature.explore.ExploreUiState.Loading
import com.github.artnehay.insightnews.feature.explore.ExploreUiState.Success

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = hiltViewModel(),
) {
    when (viewModel.exploreUiState) {
        is Loading -> {}

        is Success -> ResultScreen(
            viewModel = viewModel,
            modifier = modifier,
        )

        is Error -> {
            val uiState = (viewModel.exploreUiState as Error)
            ErrorScreen(
                iconId = uiState.errorIconId,
                messageId = uiState.message,
                onRetryClick = { viewModel.fetchTopHeadlines() },
                modifier = modifier,
            )
        }
    }
}

@Composable
fun ResultScreen(
    viewModel: ExploreViewModel,
    modifier: Modifier = Modifier,
) {
    val screenUiState = (viewModel.exploreUiState as Success)

    LazyColumn(modifier.fillMaxSize()) {
        item {
            Column {
                SearchBarButton()

                Spacer(Modifier.height(dimensionResource(medium_content_spacer)))

                Text(
                    text = stringResource(R.string.top_headlines_section),
                    style = MaterialTheme.typography.headlineSmall,
                )

                Spacer(
                    Modifier
                        .height(dimensionResource(small_content_spacer))
                        .testTag("Explore"))
            }
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(medium_content_spacer)
                ),
            ) {
                items(
                    items = screenUiState.topHeadlines,
                    key = { it.url },
                ) { headline ->
                    HeadlineCard(
                        article = headline,
                        onSavedChange = { saved ->
                            if (saved) {
                                viewModel.saveToDatabase(headline)
                            } else {
                                viewModel.removeFromDatabase(headline)
                            }
                        },
                        timeCaption = screenUiState.urlToTimeCaption[headline.url] ?: "",
                    )
                }
            }
        }

        item {
            Column {
                Spacer(Modifier.height(dimensionResource(large_content_spacer)))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(medium_content_spacer)
                    )
                ) {
                    items(10) {
                        Text(
                            text = "Topic ${it + 1}",
                            style = MaterialTheme.typography.headlineSmall,
                        )
                    }
                }
            }
        }

        items(
            items = screenUiState.topHeadlines,
            key = { it.url },
        ) { article ->
            ArticleCard(
                article = article,
                modifier = Modifier.padding(vertical = dimensionResource(medium_content_spacer)),
                timeCaption = screenUiState.urlToTimeCaption[article.url] ?: "",
            )
            if (article != screenUiState.topHeadlines.last()) {
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun SearchBarButton(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = dimensionResource(medium_content_padding)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(search_icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(medium_content_padding))
                    .size(dimensionResource(extra_small_icon_size)),
            )

            Text(
                text = stringResource(R.string.search_content_description),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
fun HeadlineCard(
    article: Article,
    onSavedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    timeCaption: String = "",
) {
    val isInDatabase by article.isInDatabase
    Card(
        onClick = {},
        modifier = modifier
            .width(dimensionResource(R.dimen.headline_card_width))
            .height(dimensionResource(R.dimen.headline_card_height)),
    ) {
        Box(
            modifier = Modifier
                .padding(dimensionResource(medium_content_padding))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(article.urlToImage)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .height(dimensionResource(R.dimen.headline_image_height))
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.small),
                    contentScale = ContentScale.FillBounds,
                )

                Spacer(Modifier.height(dimensionResource(small_content_spacer)))

                Text(
                    text = article.title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    style = MaterialTheme.typography.headlineSmall,
                )

                Row(
                    modifier = Modifier
                        .padding(vertical = dimensionResource(medium_content_padding)),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(
                            SourceToFaviconMap.getOrDefault(
                                key = article.source.id,
                                defaultValue = unknown_source_favicon,
                            )
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(dimensionResource(extra_small_icon_size))
                            .clip(CircleShape),
                        contentScale = ContentScale.FillBounds,
                    )

                    Spacer(Modifier.width(dimensionResource(small_content_spacer)))

                    Text(
                        text = article.source.name,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelMedium,
                    )
                }

                Text(
                    text = timeCaption,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    maxLines = 1,
                    style = MaterialTheme.typography.labelMedium,
                )
            }

            IconToggleButton(
                checked = isInDatabase,
                onCheckedChange = onSavedChange,
                modifier = Modifier.align(Alignment.TopEnd),
                colors = IconButtonDefaults.iconToggleButtonColors(
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                )
            ) {
                Icon(
                    painter = painterResource(
                        if (isInDatabase) save_icon_filled
                        else save_icon_outline
                    ),
                    contentDescription = stringResource(R.string.bookmarks_save_content_description),
                    modifier = Modifier.size(dimensionResource(small_icon_size)),
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun SearchBarButtonPreview() {
    InsightNewsTheme {
        SearchBarButton()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun HeadlineCardPreview() {
    InsightNewsTheme {
        HeadlineCard(
            article = FakeArticle1,
            onSavedChange = {}
        )
    }
}