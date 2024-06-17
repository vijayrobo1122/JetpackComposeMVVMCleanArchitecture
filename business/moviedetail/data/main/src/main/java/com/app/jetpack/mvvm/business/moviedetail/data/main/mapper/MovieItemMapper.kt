package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.MovieItemEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import com.app.jetpack.mvvm.common.domain.api.Mapper
import com.app.jetpack.mvvm.common.general.extensions.orFalse
import javax.inject.Inject

class MovieItemMapper @Inject constructor() : Mapper<MovieItemEntity, MovieItem> {
    override fun mapTo(type: MovieItemEntity?): MovieItem {
        return MovieItem(
            isAdult = type?.adult.orFalse(),
            backdropPath = type?.backdropPath.orEmpty(),
            genreIdsList = type?.genreIds.orEmpty(),
            id = type?.id ?: 0,
            originalLanguage = type?.originalLanguage.orEmpty(),
            originalTitle = type?.originalTitle.orEmpty(),
            overview = type?.overview.orEmpty(),
            popularity = type?.popularity ?: 0.0,
            posterPath = type?.posterPath.orEmpty(),
            releaseDate = type?.releaseDate.orEmpty(),
            title = type?.title.orEmpty(),
            isVideo = type?.video.orFalse(),
            voteAverage = type?.voteAverage ?: 0.0,
            voteCount = type?.voteCount ?: 0,
        )
    }
}
