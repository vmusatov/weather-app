package com.weatherapp.feature_home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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