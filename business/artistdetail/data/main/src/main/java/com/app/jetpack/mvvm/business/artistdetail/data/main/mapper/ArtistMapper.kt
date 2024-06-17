package com.app.jetpack.mvvm.business.artistdetail.data.main.mapper

import com.app.jetpack.mvvm.business.artistdetail.data.entity.ArtistEntity
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Artist
import com.app.jetpack.mvvm.common.domain.api.Mapper
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
