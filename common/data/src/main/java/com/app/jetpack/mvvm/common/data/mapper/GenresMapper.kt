package com.app.jetpack.mvvm.common.data.mapper

import com.app.jetpack.mvvm.common.data.entity.GenresEntity
import com.app.jetpack.mvvm.common.data.mapper.moviesdetail.GenreMapper
import com.app.jetpack.mvvm.common.domain.model.Genres
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
