package com.weatherapp.feature_home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.weatherapp.core_design_system.R

@Composable
fun HumidityText(modifier: Modifier = Modifier, value: String) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(13.dp)
                .padding(top = 2.dp),
            painter = painterResource(id = R.drawable.ic_humidity),
            contentDescription = null
        )
        Text(
            text = value,
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun LocationText(
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    text: String,
) {
    val localDensity = LocalDensity.current
    var iconHeight by remember { mutableStateOf(0.dp) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    iconHeight = with(localDensity) { (coordinates.size.height / 1.2f).toDp() }
                },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = text,
            style = style
        )
        if (text.isNotEmpty()) {
            Icon(
                modifier = Modifier.size(iconHeight),
                imageVector = Icons.Rounded.LocationOn,
                tint = MaterialTheme.colors.secondary,
                contentDescription = null
            )
        }
    }
}