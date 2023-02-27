package com.weatherapp.feature_home.presentation.components

import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.weatherapp.core_design_system.theme.LightGray2
import kotlin.math.roundToInt

private const val Y_OFFSET = 50
private const val STROKE_WIDTH = 5f
private const val DIVIDER_WIDTH = 3f

private enum class PointPosition {
    START, MIDDLE, END
}

@Composable
fun HourlyGraphItem(
    modifier : Modifier,
    valuesRange: IntRange,
    startValue: Float?,
    value: Float,
    endValue: Float?,
    fillColor: Color,
    backgroundColor: Color = MaterialTheme.colors.onPrimary,
    strokeColor: Color = LightGray2
) {
    val controlPoints1 = remember(value) { mutableListOf<PointF>() }
    val controlPoints2 = remember(value) { mutableListOf<PointF>() }
    val coordinates = remember(value) { mutableListOf<PointF>() }

    Box(
        modifier = modifier.padding(top = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            controlPoints1.clear()
            controlPoints2.clear()
            coordinates.clear()

            calculateCoordinates(
                xPosition = PointPosition.START,
                value = startValue ?: value,
                valuesRange = valuesRange,
                coordinates = coordinates
            )
            calculateCoordinates(
                xPosition = PointPosition.MIDDLE,
                value = value,
                valuesRange = valuesRange,
                coordinates = coordinates
            )
            calculateCoordinates(
                xPosition = PointPosition.END,
                value = endValue ?: value,
                valuesRange = valuesRange,
                coordinates = coordinates
            )

            for (i in 1 until coordinates.size) {
                controlPoints1.add(PointF((coordinates[i].x + coordinates[i - 1].x) / 2, coordinates[i - 1].y))
                controlPoints2.add(PointF((coordinates[i].x + coordinates[i - 1].x) / 2, coordinates[i].y))
            }

            val stroke = Path().apply {
                reset()
                moveTo(coordinates.first().x, coordinates.first().y)
                for (i in 0 until coordinates.size - 1) {
                    cubicTo(
                        controlPoints1[i].x, controlPoints1[i].y,
                        controlPoints2[i].x, controlPoints2[i].y,
                        coordinates[i + 1].x, coordinates[i + 1].y
                    )
                }
            }
            val fillPath = android.graphics.Path(stroke.asAndroidPath())
                .asComposePath()
                .apply {
                    lineTo(x = size.width, y = size.height)
                    lineTo(x = 0f, y = size.height)
                    close()
                }
            val dividerPath = Path().apply {
                reset()
                moveTo(0f, 0f)
                lineTo(0f, size.height)
            }

            drawPath(
                path = dividerPath,
                color = backgroundColor,
                style = Stroke(width = DIVIDER_WIDTH, cap = StrokeCap.Round)
            )
            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(fillColor, Color.Transparent),
                    endY = size.height,
                    tileMode = TileMode.Decal
                ),
            )
            drawPath(
                path = stroke,
                color = strokeColor,
                style = Stroke(width = STROKE_WIDTH, cap = StrokeCap.Round)
            )
        }
    }
}

private fun DrawScope.calculateCoordinates(
    xPosition: PointPosition,
    value: Float?,
    valuesRange: IntRange,
    coordinates: MutableList<PointF>
) {
    if (value == null) return

    val xValue = when(xPosition) {
        PointPosition.START ->  0f
        PointPosition.MIDDLE -> size.width / 2f
        PointPosition.END -> size.width
    }
    val yValue = convertRange(
        number = value.roundToInt(),
        original = valuesRange,
        target = Y_OFFSET..size.height.roundToInt(),
        height = size.height.roundToInt()
    ).toFloat() - Y_OFFSET

    coordinates.add(PointF(xValue, yValue))
}

private fun convertRange(number: Int, original: IntRange, target: IntRange, height: Int): Int {
    val ratio = (number - original.first).toFloat() / (original.last - original.first)
    return - (ratio * (target.last - target.first)).roundToInt() + height
}