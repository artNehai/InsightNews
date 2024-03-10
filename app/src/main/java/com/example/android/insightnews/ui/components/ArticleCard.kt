package com.example.android.insightnews.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.android.insightnews.R
import com.example.android.insightnews.ui.screens.EditorCardPlaceholder
import com.example.android.insightnews.ui.theme.InsightNewsTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(dimensionResource(R.dimen.article_card_height))
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
        ) {
            Row(
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
                    text = EditorCardPlaceholder.resourceName,
                    maxLines = 1,
                    style = MaterialTheme.typography.labelMedium,
                )
            }

            Spacer(Modifier.height(dimensionResource(R.dimen.extra_small_content_spacer)))

            Text(
                text = EditorCardPlaceholder.title,
                maxLines = 2,
                style = MaterialTheme.typography.headlineSmall,
            )

            Text(
                text = "${EditorCardPlaceholder.addedTime} | ${EditorCardPlaceholder.timeToRead}",
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.Bottom),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.labelMedium,
            )
        }

        Spacer(Modifier.width(dimensionResource(R.dimen.medium_content_spacer)))

        Image(
            painter = painterResource(EditorCardPlaceholder.image),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = dimensionResource(R.dimen.small_content_padding))
                .size(dimensionResource(R.dimen.article_card_image_size)),
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleCardPreview() {
    InsightNewsTheme {
        ArticleCard()
    }
}