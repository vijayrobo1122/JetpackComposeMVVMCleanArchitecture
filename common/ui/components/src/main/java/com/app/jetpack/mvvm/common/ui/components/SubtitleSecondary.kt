package com.app.jetpack.mvvm.common.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.app.jetpack.mvvm.common.ui.theme.subTitleSecondary

@Composable
fun SubtitleSecondary(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.subTitleSecondary
    )
}
