package com.github.artnehay.insightnews.ui.navigation.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.artnehay.insightnews.R.dimen.drawer_width
import com.github.artnehay.insightnews.core.testing.fake.FakeArticle1
import com.github.artnehay.insightnews.core.ui.R
import com.github.artnehay.insightnews.core.ui.theme.InsightNewsTheme
import com.github.artnehay.insightnews.feature.explore.HeadlineCard
import com.github.artnehay.insightnews.ui.navigation.NavigationDestination
import com.github.artnehay.insightnews.ui.navigation.StartDestination

@Composable
fun InsightNavDrawer(
    currentDestination: NavigationDestination,
    onItemClick: (NavigationDestination) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(
                modifier = Modifier.width(dimensionResource(drawer_width)),
                windowInsets = WindowInsets(
                    left = dimensionResource(com.github.artnehay.insightnews.R.dimen.horizontal_drawer_margin),
                    top = dimensionResource(com.github.artnehay.insightnews.R.dimen.top_drawer_margin),
                    right = dimensionResource(com.github.artnehay.insightnews.R.dimen.horizontal_drawer_margin),
                ),
            ) {
                for (navItem in NavigationItems) {
                    NavigationDrawerItem(
                        selected = currentDestination == navItem,
                        onClick = { onItemClick(navItem) },
                        icon = {
                            Icon(
                                painter = painterResource(navItem.iconId),
                                contentDescription = null,
                                modifier = Modifier.size(dimensionResource(R.dimen.medium_icon_size))
                            )
                        },
                        modifier = Modifier.testTag(stringResource(navItem.testLabelId)),
                        label = { Text(stringResource(navItem.labelId)) },
                    )
                }
            }
        },
        modifier = modifier,
        content = content,
    )
}

@Preview(showBackground = true)
@Composable
fun InsightNavDrawerPreview() {
    InsightNewsTheme {
        InsightNavDrawer(
            currentDestination = StartDestination,
            onItemClick = {},
            content = { HeadlineCard(article = FakeArticle1, onSavedChange = {}) },
        )
    }
}