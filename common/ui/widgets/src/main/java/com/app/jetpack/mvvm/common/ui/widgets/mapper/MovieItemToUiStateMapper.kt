package com.app.jetpack.mvvm.common.ui.widgets.mapper

import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import com.app.jetpack.mvvm.common.ui.widgets.model.MovieItemState
import javax.inject.Inject

class MovieItemToUiStateMapper @Inject constructor() {

    fun map(param: MovieItem): MovieItemState {
        return MovieItemState(
            id = param.id.toString(),
            movieId = param.id,
            voteCount = param.voteCount,
            backdropPath = param.backdropPath,
            originalLanguage = param.originalLanguage,
            originalTitle = param.originalTitle,
            overview = param.overview,
            posterPath = param.posterPath,
            releaseDate = param.releaseDate,
            title = param.title,
            popularity = param.popularity,
            voteAverage = param.voteAverage,
            isAdult = param.isAdult,
            isVideo = param.isVideo,
            genreIdsList = param.genreIdsList,
        )
    }
}
