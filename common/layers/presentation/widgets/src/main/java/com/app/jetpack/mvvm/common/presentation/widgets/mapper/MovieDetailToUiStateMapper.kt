package com.app.jetpack.mvvm.common.presentation.widgets.mapper

import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieDetail
import com.app.jetpack.mvvm.common.presentation.widgets.model.MovieDetailState
import javax.inject.Inject

class MovieDetailToUiStateMapper @Inject constructor(
    private val belongsToCollectionToUiStateMapper: BelongsToCollectionToUiStateMapper,
    private val genreToUiStateMapper: GenreToUiStateMapper,
    private val productionCompanyToUiStateMapper: ProductionCompanyToUiStateMapper,
    private val productionCountryToUiStateMapper: ProductionCountryToUiStateMapper,
    private val spokenLanguageToUiStateMapper: SpokenLanguageToUiStateMapper
) {

    fun map(param: MovieDetail): MovieDetailState {
        return MovieDetailState(
            id = param.id.toString(),
            movieId = param.id,
            budget = param.budget,
            revenue = param.revenue,
            runtime = param.runtime,
            voteCount = param.voteCount,
            backdropPath = param.backdropPath,
            homepage = param.homepage,
            imdbId = param.imdbId,
            originalLanguage = param.originalLanguage,
            originalTitle = param.originalTitle,
            overview = param.overview,
            posterPath = param.posterPath,
            releaseDate = param.releaseDate,
            status = param.status,
            tagline = param.tagline,
            title = param.title,
            popularity = param.popularity,
            voteAverage = param.voteAverage,
            isAdult = param.isAdult,
            isVideo = param.isVideo,
            isFavorite = param.isFavorite,
            belongsToCollection = belongsToCollectionToUiStateMapper.map(param.belongsToCollection),
            genresList = param.genresList.map { genreToUiStateMapper.map(it) },
            productionCompaniesList = param.productionCompaniesList.map {
                productionCompanyToUiStateMapper.map(
                    it
                )
            },
            productionCountriesList = param.productionCountriesList.map {
                productionCountryToUiStateMapper.map(
                    it
                )
            },
            spokenLanguagesList = param.spokenLanguagesList.map {
                spokenLanguageToUiStateMapper.map(
                    it
                )
            },
        )
    }
}
