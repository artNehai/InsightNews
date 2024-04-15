package com.github.artnehay.insightnews.feature.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.artnehay.insightnews.core.ui.NoticeIcon
import com.github.artnehay.insightnews.core.ui.R.dimen.medium_content_spacer
import com.github.artnehay.insightnews.core.ui.theme.InsightNewsTheme

@Composable
fun SavedScreen(
    modifier: Modifier = Modifier,
    viewModel: SavedViewModel = hiltViewModel(),
) {
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
}

@Preview(showBackground = true)
@Composable
private fun SavedArticlesScreenPreview() {
    InsightNewsTheme {
        SavedScreen()
    }
}