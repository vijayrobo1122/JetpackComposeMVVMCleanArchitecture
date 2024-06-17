package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.GenreEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genre
import com.app.jetpack.mvvm.common.domain.api.Mapper
import javax.inject.Inject

class GenreMapper @Inject constructor() : Mapper<GenreEntity, Genre> {

    override fun mapTo(type: GenreEntity?): Genre {
        return Genre(
            id = type?.id,
            name = type?.name.orEmpty(),
        )
    }
}
