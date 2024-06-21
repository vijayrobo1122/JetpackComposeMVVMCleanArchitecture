package com.app.jetpack.mvvm.common.ui.compositions

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import com.app.jetpack.mvvm.common.general.extensions.hourMinutes
import com.app.jetpack.mvvm.common.general.extensions.roundTo
import com.app.jetpack.mvvm.common.presentation.widgets.model.MovieDetailState
import com.app.jetpack.mvvm.common.ui.components.CustomImage
import com.app.jetpack.mvvm.common.ui.components.SubtitlePrimary
import com.app.jetpack.mvvm.common.ui.components.SubtitleSecondary
import com.app.jetpack.mvvm.common.ui.resources.strings.StringResources
import com.app.jetpack.mvvm.common.ui.theme.FontColor

@Composable
fun MovieDetailCard(
    modifier: Modifier = Modifier,
    movieDetailState: MovieDetailState,
    toggleFavorite: (Boolean) -> Unit,
) {
    var isFavoriteState by remember { mutableStateOf(movieDetailState.isFavorite) }
    val imageUrl = BuildConfig.IMAGE_BASE_URL.plus(movieDetailState.posterPath)
    var dominantColor by remember { mutableStateOf(Color.Gray) }

    // Extract colors from the network image
    extractColorsFromNetworkImage(imageUrl) { palette ->
        dominantColor = Color(palette.getDominantColor(Color.Gray.toArgb()))
    }

    Column(modifier = modifier) {
        Box(modifier = Modifier) {
            CustomImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                imagePath = imageUrl
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .background(
                        color = dominantColor.copy(alpha = 0.3f),
                        shape = CircleShape
                    )
            ) {
                IconButton(
                    onClick = {
                        isFavoriteState = !isFavoriteState
                        toggleFavorite(isFavoriteState)
                    },
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    Icon(
                        imageVector = if (isFavoriteState) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = if (isFavoriteState) "Unfavorite" else "Favorite",
                        tint = Color.Red,
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = movieDetailState.title,
                modifier = Modifier.padding(top = 10.dp),
                color = FontColor,
                fontSize = 30.sp,
                fontWeight = FontWeight.W700,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, top = 10.dp)
            ) {
                DisplayRowItemContent(
                    Modifier.weight(1f),
                    movieDetailState.originalLanguage,
                    StringResources.labelLanguage
                )
                DisplayRowItemContent(
                    Modifier.weight(1f),
                    movieDetailState.voteAverage.roundTo(1).toString(),
                    StringResources.labelRating,
                )
                DisplayRowItemContent(
                    Modifier.weight(1f),
                    movieDetailState.runtime.hourMinutes(),
                    StringResources.labelDuration,
                )
                DisplayRowItemContent(
                    Modifier.weight(1f),
                    movieDetailState.releaseDate,
                    StringResources.labelReleaseDate,
                )
            }
            Text(
                text = stringResource(StringResources.labelDescription),
                color = FontColor,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
            ExpandingText(text = movieDetailState.overview)
        }
    }
}

@Composable
fun DisplayRowItemContent(modifier: Modifier, title: String, iconId: Int) {
    Column(modifier = modifier) {
        SubtitlePrimary(
            text = title,
        )
        SubtitleSecondary(
            text = stringResource(iconId)
        )
    }
}

@Composable
fun extractColorsFromNetworkImage(imageUrl: String, onPaletteGenerated: (Palette) -> Unit) {
    val context = LocalContext.current
    val imageLoader = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(imageUrl)
        .allowHardware(false) // Disable hardware bitmaps for Palette support
        .listener(onSuccess = { _, result ->
            val bitmap = (result.drawable as BitmapDrawable).bitmap
            Palette.from(bitmap).generate { palette ->
                palette?.let { onPaletteGenerated(it) }
            }
        })
        .build()

    DisposableEffect(imageUrl) {
        val disposable = imageLoader.enqueue(request)
        onDispose {
            disposable.dispose()
        }
    }
}
