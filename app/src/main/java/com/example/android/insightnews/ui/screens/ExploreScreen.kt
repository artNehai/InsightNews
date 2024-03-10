package com.example.android.insightnews.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.android.insightnews.R
import com.example.android.insightnews.ui.theme.InsightNewsTheme

@Composable
fun ExploreScreen(
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

                Spacer(Modifier.height(dimensionResource(R.dimen.medium_content_spacer)))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.large_content_spacer)),
                ) {
                    items(5) {
                        EditorChoiceCard()
                    }
                }

                Spacer(Modifier.height(dimensionResource(R.dimen.extra_large_content_spacer)))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.large_content_spacer)),
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
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = {},
        modifier = modifier
            .width(dimensionResource(R.dimen.editor_choice_card_width))
            .height(dimensionResource(R.dimen.editor_choice_card_height)),
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.medium_content_padding)),
        ) {
            Image(
                painter = painterResource(EditorCardPlaceholder.image),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
            )

            Spacer(Modifier.height(dimensionResource(R.dimen.medium_content_spacer)))

            Text(
                text = EditorCardPlaceholder.title,
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

                Spacer(Modifier.width(dimensionResource(R.dimen.medium_content_spacer)))

                Text(
                    text = EditorCardPlaceholder.resourceName,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    style = MaterialTheme.typography.labelMedium,
                )
            }

            Text(
                text = "${EditorCardPlaceholder.addedTime} | ${EditorCardPlaceholder.timeToRead}",
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                style = MaterialTheme.typography.labelMedium,
            )
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

@Preview(
    showBackground = true,
)
@Composable
private fun ExploreScreenPreview() {
    InsightNewsTheme {
        ExploreScreen()
    }
}