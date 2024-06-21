package com.app.jetpack.mvvm.common.presentation.widgets.mapper

import com.app.jetpack.mvvm.business.artistdetail.domain.model.ArtistDetail
import com.app.jetpack.mvvm.common.presentation.widgets.model.ArtistDetailState
import javax.inject.Inject

class ArtistDetailToUiStateMapper @Inject constructor() {

    fun map(param: ArtistDetail): ArtistDetailState {
        return ArtistDetailState(
            id = param.id.toString(),
            artistId = param.id,
            gender = param.gender,
            biography = param.biography,
            birthday = param.birthday,
            homepage = param.homepage,
            imdbId = param.imdbId,
            knownForDepartment = param.knownForDepartment,
            name = param.name,
            placeOfBirth = param.placeOfBirth,
            profilePath = param.profilePath,
            popularity = param.popularity,
            isAdult = param.isAdult,
            alsoKnownAsList = param.alsoKnownAsList,
        )
    }
}
