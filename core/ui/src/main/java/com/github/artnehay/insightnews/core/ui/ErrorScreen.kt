package com.github.artnehay.insightnews.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.artnehay.insightnews.core.ui.theme.InsightNewsTheme

@Composable
fun ErrorScreen(
    @DrawableRes iconId: Int,
    message: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            NoticeIcon(iconId)

            Spacer(Modifier.height(dimensionResource(R.dimen.medium_content_spacer)))

            Text(
                text = message,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
            )

            Spacer(Modifier.height(dimensionResource(R.dimen.medium_content_spacer)))

            OutlinedButton(
                onClick = onRetryClick,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                ),
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                )
            ) {
                Text(
                    text = stringResource(R.string.retry_action),
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorScreenPreview() {
    InsightNewsTheme {
        ErrorScreen(
            iconId = R.drawable.wifi_off_icon,
            message = "Error",
            onRetryClick = {},
        )
    }
}