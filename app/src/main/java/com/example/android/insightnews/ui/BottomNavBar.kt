package com.example.android.insightnews.ui

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
import com.example.android.insightnews.R
import com.example.android.insightnews.ui.theme.InsightNewsTheme

@Composable
fun BottomNavBar(
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
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            painter = painterResource(navItem.icon),
                            contentDescription = null,
                            modifier = Modifier.size(dimensionResource(R.dimen.large_icon_size))
                        )
                    },
                    label = { Text(stringResource(navItem.label)) }
                )
            }
        }
    }
}

val BottomNavItems = listOf(
    NavigationItem(
        icon = R.drawable.grid_icon,
        label = R.string.feed_nav_label,
    ),
    NavigationItem(
        icon = R.drawable.explore_icon,
        label = R.string.explore_nav_label,
    ),
    NavigationItem(
        icon = R.drawable.save_icon_outline,
        label = R.string.saved_nav_label,
    ),
    NavigationItem(
        icon = R.drawable.user_icon,
        label = R.string.profile_nav_label,
    ),
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BottomNavBarPreview() {
    InsightNewsTheme {
        BottomNavBar()
    }
}