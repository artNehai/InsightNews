package com.github.artnehay.insightnews.feature.explore

import androidx.annotation.DrawableRes
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.artnehay.insightnews.core.model.Article
import com.github.artnehay.insightnews.core.ui.ArticleCard
import com.github.artnehay.insightnews.core.ui.theme.InsightNewsTheme

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier.fillMaxSize()) {
        item {
            Column {
                Text(
                    text = stringResource(R.string.explore_screen_title),
                    style = MaterialTheme.typography.titleMedium,
                )

                SearchBarButton(
                    modifier = Modifier
                        .padding(vertical = dimensionResource(R.dimen.large_content_padding)),
                )

                Text(
                    text = stringResource(R.string.editor_choice_section),
                    style = MaterialTheme.typography.headlineSmall,
                )

                Spacer(Modifier.height(dimensionResource(R.dimen.small_content_spacer)))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_content_spacer)),
                ) {
                    items(viewModel.exploreUiState.topHeadlines) {
                        EditorChoiceCard(article = it)
                    }
                }

                Spacer(Modifier.height(dimensionResource(R.dimen.large_content_spacer)))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_content_spacer)),
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

        items(5) {
            ArticleCard(
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.medium_content_spacer))
            )
            if (it < 4) {
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
                .padding(vertical = dimensionResource(R.dimen.medium_content_padding)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.search_icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(R.dimen.medium_content_padding))
                    .size(dimensionResource(R.dimen.small_icon_size)),
            )

            Text(
                text = stringResource(R.string.search_content_description),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
fun EditorChoiceCard(
    article: Article,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = {},
        modifier = modifier
            .width(dimensionResource(R.dimen.editor_choice_card_width))
            .height(dimensionResource(R.dimen.editor_choice_card_height)),
    ) {
        Box(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.medium_content_padding))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Image(
                    painter = painterResource(EditorCardPlaceholder.image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                )

                Spacer(Modifier.height(dimensionResource(R.dimen.small_content_spacer)))

                Text(
                    text = article.title,
                    maxLines = 2,
                    style = MaterialTheme.typography.headlineSmall,
                )

                Row(
                    modifier = Modifier
                        .padding(vertical = dimensionResource(R.dimen.medium_content_padding)),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(EditorCardPlaceholder.resourceImage),
                        contentDescription = null,
                        modifier = Modifier
                            .size(dimensionResource(R.dimen.small_icon_size))
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(Modifier.width(dimensionResource(R.dimen.small_content_spacer)))

                    Text(
                        text = article.source.name,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelMedium,
                    )
                }

                Text(
                    text = "${article.publishedAt} | ${EditorCardPlaceholder.timeToRead}",
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    style = MaterialTheme.typography.labelMedium,
                )
            }

            var isSaved by rememberSaveable { mutableStateOf(false) }
            IconToggleButton(
                checked = isSaved,
                onCheckedChange = { isSaved = !isSaved },
                modifier = Modifier.align(Alignment.TopEnd),
                colors = IconButtonDefaults.iconToggleButtonColors(
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                )
            ) {
                Icon(
                    painter = painterResource(
                        if (isSaved) R.drawable.save_icon_filled
                        else R.drawable.save_icon_outline
                    ),
                    contentDescription = stringResource(R.string.bookmarks_save_content_description),
                    modifier = Modifier.size(dimensionResource(R.dimen.medium_icon_size)),
                )
            }
        }
    }
}

object EditorCardPlaceholder {
    @DrawableRes
    val image = R.drawable.article_image_placeholder
    const val title = "Is Blender the Future of 3D modeling and VFX?"

    @DrawableRes
    val resourceImage = R.drawable.article_image_placeholder
    val resourceName = "UX Collective"
    val addedTime = "20 min ago"
    val timeToRead = "6 min read"
}

@Preview(showBackground = true)
@Composable
private fun ExploreScreenPreview() {
    InsightNewsTheme {
        ExploreScreen(viewModel())
    }
}