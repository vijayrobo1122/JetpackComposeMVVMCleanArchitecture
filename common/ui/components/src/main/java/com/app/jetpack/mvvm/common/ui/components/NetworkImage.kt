package com.app.jetpack.mvvm.common.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun NetworkImage(
    modifier: Modifier,
    imageUrl: String,
    contentDescription: String?,
) {
    var shouldLoadImage by remember { mutableStateOf(false) }
    var imageError by remember { mutableStateOf(false) }

    val transition = updateTransition(targetState = shouldLoadImage, label = "")
    val alpha by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 1000) }, label = ""
    ) { isLoading ->
        if (!isLoading) 0f else 1f
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = imageUrl)
                    .listener(
                        onSuccess = { request, result ->
                            shouldLoadImage = true
                        },
                        onError = { request, error ->
                            println("vijay image error : " + error.throwable.message)
                            shouldLoadImage = true
                            imageError = true
                        }
                    )
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                    }).build()
            ),
            contentDescription = contentDescription,
            modifier = modifier
                .graphicsLayer(alpha = alpha),
            contentScale = ContentScale.Crop
        )

        if (!shouldLoadImage) {
            Box(
                modifier = modifier
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
        if (imageError) {
            Box(modifier = modifier.background(Color.LightGray))
        }
    }

    DisposableEffect(imageUrl) {
        onDispose {
            // Cleanup if needed
        }
    }
}
