package com.weatherapp.core_design_system.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.weatherapp.core_design_system.theme.AppTheme

@Composable
fun AppTopBar(
    title: @Composable () -> Unit,
    navigationIcon: ImageVector? = Icons.Rounded.ArrowBack,
    onNavIconClick: (() -> Unit) = {},
    elevation: Dp = 0.dp,
    contentRight: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = title,
        navigationIcon = navigationIcon?.let {
            {
                AppIconButton(icon = it, onClick = onNavIconClick)
            }
        },
        actions = contentRight,
        contentColor = Color.Black,
        elevation = elevation
    )
}

@Preview
@Composable
private fun AppBarPreview() {
    AppTheme {
        AppTopBar(
            title = { Text(text = "AppBar") },
            contentRight = {
                AppIconButton(
                    icon = Icons.Rounded.Settings,
                    onClick = {}
                )
            },
            onNavIconClick = {}
        )
    }
}