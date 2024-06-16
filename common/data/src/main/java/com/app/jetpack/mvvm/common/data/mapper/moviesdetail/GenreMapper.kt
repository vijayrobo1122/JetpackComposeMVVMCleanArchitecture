package com.app.jetpack.mvvm.common.data.mapper.moviesdetail

import com.app.jetpack.mvvm.common.data.entity.moviedetail.GenreEntity
import com.app.jetpack.mvvm.common.data.mapper.Mapper
import com.app.jetpack.mvvm.common.domain.model.moviedetail.Genre
import javax.inject.Inject

class GenreMapper @Inject constructor() : Mapper<GenreEntity, Genre> {

    override fun mapTo(type: GenreEntity?): Genre {
        return Genre(
            id = type?.id,
            name = type?.name.orEmpty(),
        )
    }
}
