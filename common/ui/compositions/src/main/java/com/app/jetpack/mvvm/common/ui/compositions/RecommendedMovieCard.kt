package com.app.jetpack.mvvm.common.ui.compositions


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.jetpack.mvvm.common.ui.resources.strings.StringResources
import com.app.jetpack.mvvm.common.ui.theme.DefaultBackgroundColor
import com.app.jetpack.mvvm.common.ui.theme.FontColor
import com.app.jetpack.mvvm.common.ui.theme.SecondaryFontColor
import com.app.jetpack.mvvm.common.ui.theme.cornerRadius
import com.app.jetpack.mvvm.common.ui.widgets.model.MovieItemState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun RecommendedMovieCard(
    modifier: Modifier = Modifier,
    recommendedMovie: List<MovieItemState>,
    onItemClick: (String) -> Unit,
) {
    Column(modifier = modifier.padding(bottom = 10.dp)) {
        Text(
            text = stringResource(StringResources.labelSimilar),
            color = FontColor,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )
        LazyRow(modifier = Modifier.fillMaxHeight()) {
            items(recommendedMovie, itemContent = { item ->
                Column(
                    modifier = Modifier.padding(
                        start = 0.dp, end = 8.dp, top = 5.dp, bottom = 5.dp
                    )
                ) {
                    CoilImage(
                        modifier = Modifier
                            .height(190.dp)
                            .width(140.dp)
                            .cornerRadius(10)
                            .clickable {
                                onItemClick(item.id.toString())
                            },
                        imageModel = { BuildConfig.IMAGE_BASE_URL.plus(item.posterPath) },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                            contentDescription = "similar movie",
                            colorFilter = null,
                        ),
                        component = rememberImageComponent {
                            // shows a shimmering effect when loading an image.
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
            })
        }
    }
}
