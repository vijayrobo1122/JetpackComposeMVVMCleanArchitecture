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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.jetpack.mvvm.common.ui.components.CustomImage
import com.app.jetpack.mvvm.common.ui.resources.strings.StringResources
import com.app.jetpack.mvvm.common.ui.theme.FontColor
import com.app.jetpack.mvvm.common.ui.theme.cornerRadius
import com.app.jetpack.mvvm.common.ui.widgets.model.MovieItemState

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
                    CustomImage(
                        modifier = Modifier
                            .height(190.dp)
                            .width(140.dp)
                            .cornerRadius(10)
                            .clickable {
                                onItemClick(item.id.toString())
                            },
                        imagePath = BuildConfig.IMAGE_BASE_URL.plus(item.posterPath)
                    )
                }
            })
        }
    }
}
