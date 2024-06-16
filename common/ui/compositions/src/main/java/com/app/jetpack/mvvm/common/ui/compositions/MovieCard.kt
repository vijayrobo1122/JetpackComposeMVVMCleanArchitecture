package com.app.jetpack.mvvm.common.ui.compositions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.app.jetpack.mvvm.common.ui.theme.DefaultBackgroundColor
import com.app.jetpack.mvvm.common.ui.theme.SecondaryFontColor
import com.app.jetpack.mvvm.common.ui.theme.cornerRadius
import com.app.jetpack.mvvm.common.ui.widgets.model.MovieItemState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun MovieCard(
    state: MovieItemState,
    onMovieItemClick: (String) -> Unit
) {
    Column(modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 0.dp, bottom = 10.dp)) {
        CoilImage(
            modifier = Modifier
                .size(250.dp)
                .cornerRadius(10)
                .clickable {
                    onMovieItemClick(state.movieId.toString())
                },
            imageModel = { BuildConfig.IMAGE_BASE_URL.plus(state.posterPath) },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Movie item",
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
}
