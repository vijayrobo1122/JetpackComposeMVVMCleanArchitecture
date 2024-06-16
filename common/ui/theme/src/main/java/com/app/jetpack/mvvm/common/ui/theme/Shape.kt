package com.app.jetpack.mvvm.common.ui.theme


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

fun Modifier.cornerRadius(radius: Int): Modifier {
    return this.graphicsLayer(shape = RoundedCornerShape(radius.dp), clip = true)
}