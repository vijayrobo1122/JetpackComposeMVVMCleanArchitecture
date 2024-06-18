package com.app.jetpack.mvvm.common.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.app.jetpack.mvvm.common.ui.theme.DefaultBackgroundColor
import com.app.jetpack.mvvm.common.ui.theme.SecondaryFontColor
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun CustomImage(
    modifier: Modifier = Modifier,
    imagePath: String
) {
    CoilImage(
        modifier = modifier,
        imageModel = { imagePath },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            contentDescription = "artist image",
            colorFilter = null,
        ),
        component = rememberImageComponent {
            +CircularRevealPlugin(
                duration = 800
            )
            +ShimmerPlugin(
                baseColor = SecondaryFontColor,
                highlightColor = DefaultBackgroundColor
            )
        },
    )
}
