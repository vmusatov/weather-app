package com.weatherapp.core_design_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = DarkGray,
    onPrimary = DarkGray,
    secondary = WhitePrimary,
    surface = BlackPrimary,
    background = BlackPrimary,
    error = Red37
)

private val LightColorPalette = lightColors(
    primary = WhitePrimary,
    onPrimary = LightGray,
    secondary = BlackPrimary,
    surface = LightGray,
    background = WhitePrimary,
    error = Red37
)

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme) DarkColorPalette else LightColorPalette

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = if (isDarkTheme) colors.surface else colors.primary)

    MaterialTheme(
        colors = colors,
        typography = appTypography(isDarkTheme = isDarkTheme),
        content = content
    )
}