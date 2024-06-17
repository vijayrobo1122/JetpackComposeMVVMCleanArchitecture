package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.GenresEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genres
import com.app.jetpack.mvvm.common.domain.api.Mapper
import javax.inject.Inject

class GenresMapper @Inject constructor(
    private val genreMapper: GenreMapper
) : Mapper<GenresEntity, Genres> {

    override fun mapTo(type: GenresEntity?): Genres {
        return Genres(
            genres = type?.genres?.map { _genre -> genreMapper.mapTo(_genre) }
                ?: emptyList(),
        )
    }
}
