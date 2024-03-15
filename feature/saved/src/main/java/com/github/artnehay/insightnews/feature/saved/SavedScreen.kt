package com.github.artnehay.insightnews.feature.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.github.artnehay.insightnews.core.ui.theme.InsightNewsTheme

@Composable
fun SavedScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Text(
                text = stringResource(R.string.saved_screen_title),
                style = MaterialTheme.typography.titleMedium,
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.book_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.extra_large_content_padding))
                            .size(dimensionResource(R.dimen.large_icon_size)),
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                }

                Spacer(Modifier.height(dimensionResource(R.dimen.medium_content_spacer)))

                Text(
                    text = stringResource(R.string.empty_bookmarks_screen_notice),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                )
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