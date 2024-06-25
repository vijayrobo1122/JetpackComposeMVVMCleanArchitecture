package com.app.jetpack.mvvm.common.ui.compositions


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWithArrow(
    title: String?,
    pressOnBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = title ?: "",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            Image(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        pressOnBack()
                    }
            )
        },
        colors = TopAppBarDefaults.topAppBarColors()
            .copy(containerColor = MaterialTheme.colorScheme.primaryContainer),
    )
}
