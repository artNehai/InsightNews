package com.example.android.insightnews.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.android.insightnews.R

private val InterFontFamily = FontFamily(
    Font(
        resId = R.font.inter_bold,
        weight = FontWeight.Bold,
    ),
    Font(
        resId = R.font.inter_semibold,
        weight = FontWeight.SemiBold,
    ),
    Font(
        resId = R.font.inter_medium,
        weight = FontWeight.Medium,
    ),
    Font(
        resId = R.font.inter,
        weight = FontWeight.Normal,
    ),
)

val Typography = Typography()