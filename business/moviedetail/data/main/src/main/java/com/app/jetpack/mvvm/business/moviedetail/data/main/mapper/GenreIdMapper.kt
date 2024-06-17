package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.GenreIdEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.GenreId
import com.app.jetpack.mvvm.common.domain.api.Mapper
import javax.inject.Inject

class GenreIdMapper @Inject constructor() : Mapper<GenreIdEntity, GenreId> {

    override fun mapTo(type: GenreIdEntity?): GenreId {
        return GenreId(
            genreId = type?.genreId.orEmpty()
        )
    }
}
