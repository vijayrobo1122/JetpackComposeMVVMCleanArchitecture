package com.app.jetpack.mvvm.common.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val Typography.subTitlePrimary: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily.Default,
            color = FontColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }

val Typography.subTitleSecondary: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily.Default,
            color = SecondaryFontColor,
            fontSize = 10.sp,
        )
    }

val Typography.bioGrapyText: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily.Default,
            color = SecondaryFontColor,
            fontSize = 14.sp,
        )
    }