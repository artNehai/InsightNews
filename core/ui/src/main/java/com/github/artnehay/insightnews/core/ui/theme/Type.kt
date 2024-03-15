package com.github.artnehay.insightnews.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.github.artnehay.insightnews.core.ui.R

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

val Typography = Typography(
    titleLarge = TextStyle(
        fontSize = 34.sp,
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 41.sp,
    ),
    titleMedium = TextStyle(
        fontSize = 28.sp,
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 34.sp,
    ),
    titleSmall = TextStyle(
        fontSize = 22.sp,
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 28.sp,
    ),
    headlineLarge = TextStyle(
        fontSize = 20.sp,
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
    ),
    headlineMedium = TextStyle(
        fontSize = 17.sp,
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 22.sp,
    ),
    headlineSmall = TextStyle(
        fontSize = 15.sp,
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
    ),
    bodyLarge = TextStyle(
        fontSize = 17.sp,
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 22.sp,
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 21.sp,
    ),
    bodySmall = TextStyle(
        fontSize = 15.sp,
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
    ),
    labelLarge = TextStyle(
        fontSize = 13.sp,
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 18.sp,
    ),
    labelMedium = TextStyle(
        fontSize = 11.sp,
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 13.sp,
    ),
    labelSmall = TextStyle(
        fontSize = 10.sp,
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 12.sp,
    ),
)