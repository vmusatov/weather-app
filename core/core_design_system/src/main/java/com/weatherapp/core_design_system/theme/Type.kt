package com.weatherapp.core_design_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun appTypography(isDarkTheme: Boolean = isSystemInDarkTheme()): Typography {
    val color = if (isDarkTheme) WhitePrimary else BlackPrimary
    return Typography(
        h2 = TextStyle(
            fontWeight = FontWeight.W500,
            fontSize = 70.sp,
            color = color
        ),
        h4 = TextStyle(
            fontWeight = FontWeight.W400,
            fontSize = 34.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.20.sp,
            color = color
        ),
        h5 = TextStyle(
            fontWeight = FontWeight.W400,
            fontSize = 24.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.18.sp,
            color = color
        ),
        h6 = TextStyle(
            fontWeight = FontWeight.W500,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
            color = color
        ),
        body1 = TextStyle(
            fontWeight = FontWeight.W500,
            fontSize = 18.sp,
            color = color
        ),
        body2 = TextStyle(
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp,
            color = color
        ),
        subtitle1 = TextStyle(
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
            color = color
        ),
        subtitle2 = TextStyle(
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp,
            color = color
        ),
        caption = TextStyle(
            fontWeight = FontWeight.W400,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp,
            color = color
        )
    )
}