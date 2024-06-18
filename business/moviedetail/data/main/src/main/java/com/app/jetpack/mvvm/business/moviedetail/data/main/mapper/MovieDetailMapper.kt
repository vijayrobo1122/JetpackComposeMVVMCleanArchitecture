package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.MovieDetailEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieDetail
import com.app.jetpack.mvvm.common.domain.api.Mapper
import com.app.jetpack.mvvm.common.general.extensions.orFalse
import javax.inject.Inject


class MovieDetailMapper @Inject constructor(
    private val belongsToCollectionMapper: BelongsToCollectionMapper,
    private val genreMapper: GenreMapper,
    private val productionCompanyMapper: ProductionCompanyMapper,
    private val productionCountryMapper: ProductionCountryMapper,
    private val spokenLanguageMapper: SpokenLanguageMapper,
) : Mapper<MovieDetailEntity, MovieDetail> {

    override fun mapTo(type: MovieDetailEntity?): MovieDetail {
        return MovieDetail(
            isAdult = type?.isAdult.orFalse(),
            backdropPath = type?.backdropPath.orEmpty(),
            belongsToCollection = belongsToCollectionMapper.mapTo(type?.belongsToCollection),
            budget = type?.budget ?: 0,
            genresList = type?.genresList?.map { _genre -> genreMapper.mapTo(_genre) } ?: emptyList(),
            homepage = type?.homepage.orEmpty(),
            id = type?.id ?: 0,
            imdbId = type?.imdbId.orEmpty(),
            originalLanguage = type?.originalLanguage.orEmpty(),
            originalTitle = type?.originalTitle.orEmpty(),
            overview = type?.overview.orEmpty(),
            popularity = type?.popularity ?: 0.0,
            posterPath = type?.posterPath.orEmpty(),
            productionCompaniesList = type?.productionCompaniesList?.map { _company ->
                productionCompanyMapper.mapTo(
                    _company
                )
            } ?: emptyList(),
            productionCountriesList = type?.productionCountriesList?.map { _country ->
                productionCountryMapper.mapTo(
                    _country
                )
            } ?: emptyList(),
            releaseDate = type?.releaseDate.orEmpty(),
            revenue = type?.revenue ?: 0,
            runtime = type?.runtime ?: 0,
            spokenLanguagesList = type?.spokenLanguagesList?.map { _language ->
                spokenLanguageMapper.mapTo(
                    _language
                )
            } ?: emptyList(),
            status = type?.status.orEmpty(),
            tagline = type?.tagline.orEmpty(),
            title = type?.title.orEmpty(),
            isVideo = type?.isVideo.orFalse(),
            voteAverage = type?.voteAverage ?: 0.0,
            voteCount = type?.voteCount ?: 0,
        )
    }
}
