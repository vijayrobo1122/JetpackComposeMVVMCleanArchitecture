package com.app.jetpack.mvvm.common.data.mapper

import com.app.jetpack.mvvm.common.data.entity.GenreIdEntity
import com.app.jetpack.mvvm.common.domain.model.GenreId
import javax.inject.Inject

class GenreIdMapper @Inject constructor() : Mapper<GenreIdEntity, GenreId> {

    override fun mapTo(type: GenreIdEntity?): GenreId {
        return GenreId(
            genreId = type?.genreId.orEmpty()
        )
    }
}
