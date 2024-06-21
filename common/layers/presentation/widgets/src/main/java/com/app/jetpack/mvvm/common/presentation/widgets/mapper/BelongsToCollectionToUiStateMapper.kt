package com.app.jetpack.mvvm.common.presentation.widgets.mapper

import com.app.jetpack.mvvm.business.moviedetail.domain.model.BelongsToCollection
import com.app.jetpack.mvvm.common.presentation.widgets.model.BelongsToCollectionState
import javax.inject.Inject

class BelongsToCollectionToUiStateMapper @Inject constructor() {

    fun map(param: BelongsToCollection): BelongsToCollectionState {
        return BelongsToCollectionState(
            id = param.id.toString(),
            belogsToCollectionId = param.id,
            backdropPath = param.backdropPath,
            name = param.name,
            posterPath = param.posterPath,
        )
    }
}
