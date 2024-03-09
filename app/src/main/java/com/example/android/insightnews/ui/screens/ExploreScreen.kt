package com.example.android.insightnews.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            Text(
                text = stringResource(R.string.explore_screen_title),
                style = MaterialTheme.typography.titleMedium,
            )
        }
        item {
            SearchBarButton(
                modifier = Modifier
                    .padding(vertical = dimensionResource(R.dimen.large_content_padding)),
            )
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

@Preview(
    showBackground = true,
)
@Composable
private fun ExploreScreenPreview() {
    InsightNewsTheme {
        ExploreScreen()
    }
}