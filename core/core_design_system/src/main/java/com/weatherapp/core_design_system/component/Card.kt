package com.weatherapp.core_design_system.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weatherapp.core_design_system.theme.AppTheme

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleContent: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = 0.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.onPrimary
    ) {
        Column {
            if (!title.isNullOrEmpty()) {
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        CardTitleText(text = title)

                        if (titleContent != null) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .alignByBaseline(),
                                horizontalAlignment = Alignment.End
                            ) {
                                titleContent()
                            }
                        }
                    }
                )
            }
            content()
        }
    }
}

@Composable
fun CardTitleText(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.W500),
        text = text.uppercase(),
        color = MaterialTheme.colors.secondaryVariant
    )
}

@Composable
@Preview(showBackground = true)
fun AppCardPreview() {
    AppTheme {
        Column(modifier = Modifier.padding(10.dp)) {
            AppCard(title = "test") {
                Text(text = "text")
            }
        }
    }
}