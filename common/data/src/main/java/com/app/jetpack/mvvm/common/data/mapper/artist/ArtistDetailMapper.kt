package com.app.jetpack.mvvm.common.data.mapper.artist

import com.app.jetpack.mvvm.common.data.entity.artist.ArtistDetailEntity
import com.app.jetpack.mvvm.common.data.mapper.Mapper
import com.app.jetpack.mvvm.common.domain.model.artist.ArtistDetail
import com.app.jetpack.mvvm.common.general.extensions.orFalse
import javax.inject.Inject

class ArtistDetailMapper @Inject constructor( ) : Mapper<ArtistDetailEntity, ArtistDetail> {

    override fun mapTo(type: ArtistDetailEntity?): ArtistDetail {
        return ArtistDetail(
            isAdult = type?.adult.orFalse(),
            alsoKnownAsList = type?.alsoKnownAs ?: emptyList(),
            biography = type?.biography.orEmpty(),
            birthday = type?.birthday.orEmpty(),
            gender = type?.gender ?: 0,
            homepage = type?.homepage.orEmpty(),
            id = type?.id ?: 0,
            imdbId = type?.imdbId.orEmpty(),
            knownForDepartment = type?.knownForDepartment.orEmpty(),
            name = type?.name.orEmpty(),
            placeOfBirth = type?.placeOfBirth.orEmpty(),
            popularity = type?.popularity ?: 0.0,
            profilePath = type?.profilePath.orEmpty(),
        )
    }
}
