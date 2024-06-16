package com.app.jetpack.mvvm.common.data.mapper.moviesdetail

import com.app.jetpack.mvvm.common.data.entity.moviedetail.BelongsToCollectionEntity
import com.app.jetpack.mvvm.common.data.mapper.Mapper
import com.app.jetpack.mvvm.common.domain.model.moviedetail.BelongsToCollection
import javax.inject.Inject

class BelongsToCollectionMapper @Inject constructor() :
    Mapper<BelongsToCollectionEntity, BelongsToCollection> {

    override fun mapTo(type: BelongsToCollectionEntity?): BelongsToCollection {
        return BelongsToCollection(
            backdropPath = type?.backdrop_path.orEmpty(),
            id = type?.id ?: 0,
            name = type?.name.orEmpty(),
            posterPath = type?.poster_path.orEmpty(),
        )
    }
}
