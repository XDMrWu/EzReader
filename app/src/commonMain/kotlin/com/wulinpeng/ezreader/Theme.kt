package com.wulinpeng.ezreader


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp

private val shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
)

private val colors = lightColorScheme().copy(
    primary = Color.Blue, //blue
    secondary = Color(0xFF08090a), //black
    primaryContainer = Color(0xFFFFFFFF), //white
    secondaryContainer = Color(0xFFf4f4f4), //lightGray
    background = Color(0xFFf4f4f4)
)



val Color.isLight: Boolean
    get() {
        return luminance() >= 0.8
    }

@Composable
fun AppTheme(
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colorScheme = colors,
        shapes = shapes,
        content = content
    )
}