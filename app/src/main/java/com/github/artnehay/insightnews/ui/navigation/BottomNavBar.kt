package com.github.artnehay.insightnews.ui.navigation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.artnehay.insightnews.core.ui.R.dimen.medium_icon_size
import com.github.artnehay.insightnews.core.ui.theme.InsightNewsTheme
import com.github.artnehay.insightnews.ui.navigation.NavigationDestination.ExploreNavigationDestination
import com.github.artnehay.insightnews.ui.navigation.NavigationDestination.SavedNavigationDestination

val BottomNavItems = listOf(
    ExploreNavigationDestination,
    SavedNavigationDestination,
)

@Composable
fun BottomNavBar(
    currentDestination: NavigationDestination,
    onItemClick: (NavigationDestination) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        HorizontalDivider()
        NavigationBar(
            modifier = modifier,
            tonalElevation = 0.dp,
        ) {
            for (navItem in BottomNavItems) {
                NavigationBarItem(
                    selected = currentDestination == navItem,
                    onClick = { onItemClick(navItem) },
                    icon = {
                        Icon(
                            painter = painterResource(navItem.iconId),
                            contentDescription = null,
                            modifier = Modifier.size(dimensionResource(medium_icon_size))
                        )
                    },
                    label = { Text(stringResource(navItem.labelId)) },
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BottomNavBarPreview() {
    InsightNewsTheme {
        BottomNavBar(
            currentDestination = ExploreNavigationDestination,
            onItemClick = {},
        )
    }
}