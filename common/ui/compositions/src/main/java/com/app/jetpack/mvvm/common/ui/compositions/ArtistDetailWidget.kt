package com.app.jetpack.mvvm.common.ui.compositions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.jetpack.mvvm.common.general.extensions.genderInString
import com.app.jetpack.mvvm.common.ui.components.BioGraphyText
import com.app.jetpack.mvvm.common.ui.resources.strings.StringResources
import com.app.jetpack.mvvm.common.ui.theme.DefaultBackgroundColor
import com.app.jetpack.mvvm.common.ui.theme.FontColor
import com.app.jetpack.mvvm.common.ui.theme.SecondaryFontColor
import com.app.jetpack.mvvm.common.ui.theme.cornerRadius
import com.app.jetpack.mvvm.common.ui.widgets.model.ArtistDetailState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun ArtistDetailWidget(
    modifier: Modifier = Modifier,
    artistDetailState: ArtistDetailState,
) {
    Column(
        modifier = modifier
    ) {
        Row {
            CoilImage(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .height(250.dp)
                    .width(190.dp)
                    .cornerRadius(10),
                imageModel = { BuildConfig.IMAGE_BASE_URL.plus(artistDetailState.profilePath) },
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
            Column {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = artistDetailState.name,
                    color = FontColor,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium
                )
                PersonalInfo(
                    stringResource(StringResources.labelKnowFor),
                    artistDetailState.knownForDepartment
                )
                PersonalInfo(
                    stringResource(StringResources.labelGender),
                    artistDetailState.gender.genderInString()
                )
                artistDetailState.birthday?.let { birthday ->
                    PersonalInfo(
                        stringResource(StringResources.labelBirthDay),
                        birthday
                    )
                }
                artistDetailState.placeOfBirth?.let { birthPlace ->
                    PersonalInfo(
                        stringResource(StringResources.labelPlaceOfBirth),
                        birthPlace
                    )
                }
            }
        }
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = stringResource(StringResources.labelBiography),
            color = SecondaryFontColor,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )
        BioGraphyText(
            text = artistDetailState.biography
        )
    }
}
