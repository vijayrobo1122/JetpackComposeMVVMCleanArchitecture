package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.BelongsToCollectionEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BelongsToCollection
import com.app.jetpack.mvvm.common.domain.api.Mapper
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
