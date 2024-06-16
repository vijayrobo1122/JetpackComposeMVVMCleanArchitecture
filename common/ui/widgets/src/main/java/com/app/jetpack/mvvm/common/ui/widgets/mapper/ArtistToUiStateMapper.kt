package com.app.jetpack.mvvm.common.ui.widgets.mapper

import com.app.jetpack.mvvm.common.domain.model.artist.Artist
import com.app.jetpack.mvvm.common.ui.widgets.model.ArtistState
import javax.inject.Inject

class ArtistToUiStateMapper @Inject constructor(
    private val castToUiStateMapper: CastToUiStateMapper,
    private val crewToUiStateMapper: CrewToUiStateMapper,
) {

    fun map(param: Artist): ArtistState {
        return ArtistState(
            id = param.id.toString(),
            artistId = param.id,
            castList = param.castList.map { cast -> castToUiStateMapper.map(cast) },
            crewList = param.crewList.map { crew -> crewToUiStateMapper.map(crew) },
        )
    }
}
