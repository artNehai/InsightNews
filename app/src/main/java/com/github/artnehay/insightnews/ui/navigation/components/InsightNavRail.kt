package com.github.artnehay.insightnews.ui.navigation.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.artnehay.insightnews.core.ui.R
import com.github.artnehay.insightnews.core.ui.theme.InsightNewsTheme
import com.github.artnehay.insightnews.ui.navigation.NavigationDestination
import com.github.artnehay.insightnews.ui.navigation.StartDestination

@Composable
fun InsightNavigationRail(
    currentDestination: NavigationDestination,
    onItemClick: (NavigationDestination) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationRail(
        modifier = modifier,
        windowInsets = WindowInsets(
            left = dimensionResource(com.github.artnehay.insightnews.R.dimen.horizontal_drawer_margin),
            top = dimensionResource(com.github.artnehay.insightnews.R.dimen.top_drawer_margin),
            right = dimensionResource(com.github.artnehay.insightnews.R.dimen.horizontal_drawer_margin),
        ),
    ) {
        for (navItem in NavigationItems) {
            NavigationRailItem(
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
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InsightNavigationRailPreview() {
    InsightNewsTheme {
        InsightNavigationRail(
            currentDestination = StartDestination,
            onItemClick = {},
        )
    }
}