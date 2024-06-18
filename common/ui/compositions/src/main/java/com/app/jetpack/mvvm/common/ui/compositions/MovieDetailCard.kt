package com.app.jetpack.mvvm.common.ui.compositions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.jetpack.mvvm.common.general.extensions.hourMinutes
import com.app.jetpack.mvvm.common.general.extensions.roundTo
import com.app.jetpack.mvvm.common.ui.components.CustomImage
import com.app.jetpack.mvvm.common.ui.components.SubtitlePrimary
import com.app.jetpack.mvvm.common.ui.components.SubtitleSecondary
import com.app.jetpack.mvvm.common.ui.resources.strings.StringResources
import com.app.jetpack.mvvm.common.ui.theme.FontColor
import com.app.jetpack.mvvm.common.ui.widgets.model.MovieDetailState

@Composable
fun MovieDetailCard(
    modifier: Modifier = Modifier,
    movieDetailState: MovieDetailState,
) {
    Column(modifier = modifier) {
        CustomImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            imagePath = BuildConfig.IMAGE_BASE_URL.plus(movieDetailState.posterPath)
        )
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

                Column(Modifier.weight(1f)) {
                    SubtitlePrimary(
                        text = movieDetailState.originalLanguage,
                    )
                    SubtitleSecondary(
                        text = stringResource(StringResources.labelLanguage)
                    )
                }
                Column(Modifier.weight(1f)) {
                    SubtitlePrimary(
                        text = movieDetailState.voteAverage.roundTo(1).toString(),
                    )
                    SubtitleSecondary(
                        text = stringResource(StringResources.labelRating)
                    )
                }
                Column(Modifier.weight(1f)) {
                    SubtitlePrimary(
                        text = movieDetailState.runtime.hourMinutes()
                    )
                    SubtitleSecondary(
                        text = stringResource(StringResources.labelDuration)
                    )
                }
                Column(Modifier.weight(1f)) {
                    SubtitlePrimary(
                        text = movieDetailState.releaseDate
                    )
                    SubtitleSecondary(
                        text = stringResource(StringResources.labelReleaseDate)
                    )
                }
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
