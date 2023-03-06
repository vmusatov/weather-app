package com.weatherapp.core_design_system.util

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.weatherapp.core_design_system.R

fun mapTempToColor(tempC: Int): Color = if (tempC > 30) {
    tempMap[30] ?: Color.White
} else if (tempC < -30) {
    tempMap[-30] ?: Color.White
} else {
    tempMap[tempC] ?: Color.White
}

@DrawableRes
fun mapIconCode(code: Int?, isDay: Boolean): Int = when (code) {
    1000 -> if (isDay) R.drawable.ic_1000_day else R.drawable.ic_1000_night
    1003 -> if (isDay) R.drawable.ic_1003_day else R.drawable.ic_1003_night
    1006 -> if (isDay) R.drawable.ic_1006_1009_day else R.drawable.ic_1006_1009_night
    1009 -> if (isDay) R.drawable.ic_1006_1009_day else R.drawable.ic_1006_1009_night
    1030 -> if (isDay) R.drawable.ic_1030_day else R.drawable.ic_1030_night
    1063 -> if (isDay) R.drawable.ic_1063_1180_1186_1240 else R.drawable.ic_1063_1066_1180_1186_1240_night
    1066 -> if (isDay) R.drawable.ic_1066_1210_1216_1255 else R.drawable.ic_1066_1210_1216_1255
    1069 -> if (isDay) R.drawable.ic_1069_1249 else R.drawable.ic_1069_1249_night
    1072 -> if (isDay) R.drawable.ic_1072 else R.drawable.ic_1072_night
    1087 -> if (isDay) R.drawable.ic_1087_1273 else R.drawable.ic_1087_1273_night
    1114 -> if (isDay) R.drawable.ic_1114 else R.drawable.ic_1114_night
    1117 -> if (isDay) R.drawable.ic_1117 else R.drawable.ic_1117_night
    1135 -> if (isDay) R.drawable.ic_1135 else R.drawable.ic_1135_night
    1147 -> if (isDay) R.drawable.ic_1147 else R.drawable.ic_1147_night
    1150 -> if (isDay) R.drawable.ic_1150_1153 else R.drawable.ic_1150_1153_night
    1153 -> if (isDay) R.drawable.ic_1150_1153 else R.drawable.ic_1150_1153_night
    1168 -> if (isDay) R.drawable.ic_1168 else R.drawable.ic_1168_night
    1171 -> if (isDay) R.drawable.ic_1171 else R.drawable.ic_1171_night
    1180 -> if (isDay) R.drawable.ic_1063_1180_1186_1240 else R.drawable.ic_1063_1066_1180_1186_1240_night
    1183 -> if (isDay) R.drawable.ic_1183_1189 else R.drawable.ic_1183_1189_night
    1186 -> if (isDay) R.drawable.ic_1063_1180_1186_1240 else R.drawable.ic_1063_1066_1180_1186_1240_night
    1189 -> if (isDay) R.drawable.ic_1183_1189 else R.drawable.ic_1183_1189_night
    1192 -> if (isDay) R.drawable.ic_1192_1243 else R.drawable.ic_1192_1243_night
    1195 -> if (isDay) R.drawable.ic_1195 else R.drawable.ic_1195_night
    1198 -> if (isDay) R.drawable.ic_1198_1201 else R.drawable.ic_1198_1201_night
    1201 -> if (isDay) R.drawable.ic_1198_1201 else R.drawable.ic_1198_1201_night
    1204 -> if (isDay) R.drawable.ic_1204_1207 else R.drawable.ic_1204_1207_night
    1207 -> if (isDay) R.drawable.ic_1204_1207 else R.drawable.ic_1204_1207_night
    1210 -> if (isDay) R.drawable.ic_1066_1210_1216_1255 else R.drawable.ic_1210_1216_1255_night
    1213 -> if (isDay) R.drawable.ic_1213_1219 else R.drawable.ic_1213_1219_night
    1216 -> if (isDay) R.drawable.ic_1066_1210_1216_1255 else R.drawable.ic_1210_1216_1255_night
    1219 -> if (isDay) R.drawable.ic_1213_1219 else R.drawable.ic_1213_1219_night
    1222 -> if (isDay) R.drawable.ic_1222_1258 else R.drawable.ic_1222_1258_night
    1225 -> if (isDay) R.drawable.ic_1225 else R.drawable.ic_1225_night
    1237 -> if (isDay) R.drawable.ic_1237 else R.drawable.ic_1237_night
    1240 -> if (isDay) R.drawable.ic_1063_1180_1186_1240 else R.drawable.ic_1063_1066_1180_1186_1240_night
    1243 -> if (isDay) R.drawable.ic_1192_1243 else R.drawable.ic_1192_1243_night
    1246 -> if (isDay) R.drawable.ic_1246 else R.drawable.ic_1246_night
    1249 -> if (isDay) R.drawable.ic_1069_1249 else R.drawable.ic_1069_1249_night
    1252 -> if (isDay) R.drawable.ic_1252 else R.drawable.ic_1252_night
    1255 -> if (isDay) R.drawable.ic_1066_1210_1216_1255 else R.drawable.ic_1210_1216_1255_night
    1258 -> if (isDay) R.drawable.ic_1222_1258 else R.drawable.ic_1222_1258_night
    1261 -> if (isDay) R.drawable.ic_1261 else R.drawable.ic_1261_night
    1264 -> if (isDay) R.drawable.ic_1264 else R.drawable.ic_1264_night
    1273 -> if (isDay) R.drawable.ic_1087_1273 else R.drawable.ic_1087_1273_night
    1276 -> if (isDay) R.drawable.ic_1276 else R.drawable.ic_1276_night
    1279 -> if (isDay) R.drawable.ic_1279 else R.drawable.ic_1279_night
    1282 -> if (isDay) R.drawable.ic_1282 else R.drawable.ic_1282_night
    else -> if (isDay) R.drawable.ic_1000_day else R.drawable.ic_1000_night
}