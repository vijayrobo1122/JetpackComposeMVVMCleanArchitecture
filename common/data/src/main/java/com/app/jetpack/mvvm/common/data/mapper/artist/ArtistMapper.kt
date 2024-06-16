package com.app.jetpack.mvvm.common.data.mapper.artist

import com.app.jetpack.mvvm.common.data.entity.artist.ArtistEntity
import com.app.jetpack.mvvm.common.data.mapper.Mapper
import com.app.jetpack.mvvm.common.domain.model.artist.Artist
import javax.inject.Inject

class ArtistMapper @Inject constructor(
    private val castMapper: CastMapper,
    private val crewMapper: CrewMapper,
) : Mapper<ArtistEntity, Artist> {

    override fun mapTo(type: ArtistEntity?): Artist {
        return Artist(
            id = type?.id ?: 0,
            castList = type?.cast?.map { _cast -> castMapper.mapTo(_cast) } ?: emptyList(),
            crewList = type?.crew?.map { _crew -> crewMapper.mapTo(_crew) } ?: emptyList()
        )
    }
}
