package com.weatherapp.core_design_system.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AppIconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    tint: Color = MaterialTheme.colors.secondary,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = icon,
            tint = tint,
            contentDescription = null
        )
    }
}