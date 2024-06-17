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
            isAdult = type?.adult.orFalse(),
            backdropPath = type?.backdrop_path.orEmpty(),
            belongsToCollection = belongsToCollectionMapper.mapTo(type?.belongs_to_collection),
            budget = type?.budget ?: 0,
            genresList = type?.genres?.map { _genre -> genreMapper.mapTo(_genre) } ?: emptyList(),
            homepage = type?.homepage.orEmpty(),
            id = type?.id ?: 0,
            imdbId = type?.imdb_id.orEmpty(),
            originalLanguage = type?.original_language.orEmpty(),
            originalTitle = type?.original_title.orEmpty(),
            overview = type?.overview.orEmpty(),
            popularity = type?.popularity ?: 0.0,
            posterPath = type?.poster_path.orEmpty(),
            productionCompaniesList = type?.production_companies?.map { _company ->
                productionCompanyMapper.mapTo(
                    _company
                )
            } ?: emptyList(),
            productionCountriesList = type?.production_countries?.map { _country ->
                productionCountryMapper.mapTo(
                    _country
                )
            } ?: emptyList(),
            releaseDate = type?.release_date.orEmpty(),
            revenue = type?.revenue ?: 0,
            runtime = type?.runtime ?: 0,
            spokenLanguagesList = type?.spoken_languages?.map { _language ->
                spokenLanguageMapper.mapTo(
                    _language
                )
            } ?: emptyList(),
            status = type?.status.orEmpty(),
            tagline = type?.tagline.orEmpty(),
            title = type?.title.orEmpty(),
            isVideo = type?.video.orFalse(),
            voteAverage = type?.vote_average ?: 0.0,
            voteCount = type?.vote_count ?: 0,
        )
    }
}
