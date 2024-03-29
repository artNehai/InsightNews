package com.github.artnehay.insightnews.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.artnehay.insightnews.core.ui.theme.InsightNewsTheme

@Composable
fun NoticeIcon(
    @DrawableRes iconId: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = CircleShape,
        color = MaterialTheme.colorScheme.secondaryContainer,
    ) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = null,
            modifier = modifier
                .padding(dimensionResource(R.dimen.extra_large_content_padding))
                .size(dimensionResource(R.dimen.medium_icon_size)),
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    }
}

@Preview
@Composable
private fun NoticeIconPreview() {
    InsightNewsTheme {
        NoticeIcon(iconId = R.drawable.wifi_off_icon)
    }
}