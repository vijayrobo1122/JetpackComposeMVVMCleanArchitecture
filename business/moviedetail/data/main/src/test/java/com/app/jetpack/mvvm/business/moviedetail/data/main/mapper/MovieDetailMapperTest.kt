package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.BelongsToCollectionEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.GenreEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.MovieDetailEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.ProductionCompanyEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.ProductionCountryEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.SpokenLanguageEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BelongsToCollection
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genre
import com.app.jetpack.mvvm.business.moviedetail.domain.model.ProductionCompany
import com.app.jetpack.mvvm.business.moviedetail.domain.model.ProductionCountry
import com.app.jetpack.mvvm.business.moviedetail.domain.model.SpokenLanguage
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieDetailMapperTest {

    private val belongsToCollectionMapper: BelongsToCollectionMapper = mockk(relaxed = true)
    private val genreMapper: GenreMapper = mockk(relaxed = true)
    private val productionCompanyMapper: ProductionCompanyMapper = mockk(relaxed = true)
    private val productionCountryMapper: ProductionCountryMapper = mockk(relaxed = true)
    private val spokenLanguageMapper: SpokenLanguageMapper = mockk(relaxed = true)
    private val sut = MovieDetailMapper(
        belongsToCollectionMapper, genreMapper, productionCompanyMapper,
        productionCountryMapper, spokenLanguageMapper
    )

    @Test
    fun `Given a valid ArtistEntity When mapping is done Then a valid ArtistModel should be created`() {


        // Given
        val belongsToCollectionEntity = BelongsToCollectionEntity(
            id = 123,
            backdrop_path = "backdrop_path",
            name = "name",
            poster_path = "poster_path",
        )
        val belongsToCollection = mockk<BelongsToCollection>(relaxed = true) {
            every { id } returns belongsToCollectionEntity.id
            every { backdropPath } returns belongsToCollectionEntity.backdrop_path
            every { name } returns belongsToCollectionEntity.name
            every { posterPath } returns belongsToCollectionEntity.poster_path
        }
        every { belongsToCollectionMapper.mapTo(belongsToCollectionEntity) } returns belongsToCollection

        val genreEntity = GenreEntity(
            id = 234,
            name = "name",
        )
        val genre = mockk<Genre>(relaxed = true) {
            every { id } returns genreEntity.id
            every { name } returns genreEntity.name
        }
        every { genreMapper.mapTo(genreEntity) } returns genre
        val genres = listOf(genreEntity)


        val productionCompanyEntity = ProductionCompanyEntity(
            id = 234,
            logo_path = "logo_path",
            origin_country = "origin_country",
            name = "name",
        )
        val productionCompany = mockk<ProductionCompany>(relaxed = true) {
            every { id } returns productionCompanyEntity.id
            every { logoPath } returns productionCompanyEntity.logo_path
            every { originCountry } returns productionCompanyEntity.origin_country
            every { name } returns productionCompanyEntity.name
        }
        every { productionCompanyMapper.mapTo(productionCompanyEntity) } returns productionCompany
        val productionCompanies = listOf(productionCompanyEntity)


        val productionCountryEntity = ProductionCountryEntity(
            iso_3166_1 = "iso_3166_1",
            name = "name",
        )
        val productionCountry = mockk<ProductionCountry>(relaxed = true) {
            every { iso_3166_1 } returns productionCountryEntity.iso_3166_1
            every { name } returns productionCountryEntity.name
        }
        every { productionCountryMapper.mapTo(productionCountryEntity) } returns productionCountry
        val productionCountries = listOf(productionCountryEntity)

        val spokenLanguageEntity = SpokenLanguageEntity(
            english_name = "english_name",
            iso_639_1 = "iso_639_1",
            name = "name",
        )
        val spokenLanguage = mockk<SpokenLanguage>(relaxed = true) {
            every { englishName } returns spokenLanguageEntity.english_name
            every { iso_639_1 } returns spokenLanguageEntity.iso_639_1
            every { name } returns spokenLanguageEntity.name
        }
        every { spokenLanguageMapper.mapTo(spokenLanguageEntity) } returns spokenLanguage
        val spokenLanguages = listOf(spokenLanguageEntity)


        val movieDetailEntity = MovieDetailEntity(
            id = 123,
            adult = true,
            backdrop_path = "backdrop_path",
            belongs_to_collection = belongsToCollectionEntity,
            budget = 85,
            genres = genres,
            homepage = "homepage",
            imdb_id = "imdb_id",
            original_language = "original_language",
            original_title = "original_title",
            overview = "overview",
            popularity = 15.67,
            poster_path = "poster_path",
            production_companies = productionCompanies,
            production_countries = productionCountries,
            release_date = "release_date",
            revenue = 9,
            runtime = 923,
            spoken_languages = spokenLanguages,
            status = "status",
            tagline = "tagline",
            title = "title",
            video = true,
            vote_average = 89.23,
            vote_count = 89,
        )


        // When
        val movieDetailModel = sut.mapTo(movieDetailEntity)

        // Then


        assertEquals(movieDetailEntity.id, movieDetailModel.id)
        assertEquals(movieDetailEntity.adult, movieDetailModel.isAdult)
        assertEquals(movieDetailEntity.backdrop_path, movieDetailModel.backdropPath)
        assertEquals(movieDetailEntity.budget, movieDetailModel.budget)
        assertEquals(movieDetailEntity.homepage, movieDetailModel.homepage)
        assertEquals(movieDetailEntity.imdb_id, movieDetailModel.imdbId)
        assertEquals(movieDetailEntity.original_language, movieDetailModel.originalLanguage)
        assertEquals(movieDetailEntity.original_title, movieDetailModel.originalTitle)
        assertEquals(movieDetailEntity.overview, movieDetailModel.overview)
        assertEquals(movieDetailEntity.poster_path, movieDetailModel.posterPath)
        assertEquals(movieDetailEntity.release_date, movieDetailModel.releaseDate)
        assertEquals(movieDetailEntity.revenue, movieDetailModel.revenue)
        assertEquals(movieDetailEntity.runtime, movieDetailModel.runtime)
        assertEquals(movieDetailEntity.status, movieDetailModel.status)
        assertEquals(movieDetailEntity.tagline, movieDetailModel.tagline)
        assertEquals(movieDetailEntity.title, movieDetailModel.title)
        assertEquals(movieDetailEntity.video, movieDetailModel.isVideo)
        assertEquals(movieDetailEntity.vote_count, movieDetailModel.voteCount)


        val belongsToCollectionModel = movieDetailModel.belongsToCollection
        assertEquals(belongsToCollectionEntity.id, belongsToCollectionModel.id)
        assertEquals(belongsToCollectionEntity.backdrop_path, belongsToCollectionModel.backdropPath)
        assertEquals(belongsToCollectionEntity.name, belongsToCollectionModel.name)
        assertEquals(belongsToCollectionEntity.poster_path, belongsToCollectionModel.posterPath)

        val genreModel = movieDetailModel.genresList.first()
        assertEquals(genre.id, genreModel.id)
        assertEquals(genre.name, genreModel.name)

        val productionCompanyModel = movieDetailModel.productionCompaniesList.first()
        assertEquals(productionCompanyEntity.id, productionCompanyModel.id)
        assertEquals(productionCompanyEntity.logo_path, productionCompanyModel.logoPath)
        assertEquals(productionCompanyEntity.origin_country, productionCompanyModel.originCountry)
        assertEquals(productionCompanyEntity.name, productionCompanyModel.name)

        val productionCountryModel = movieDetailModel.productionCountriesList.first()
        assertEquals(productionCountryEntity.iso_3166_1, productionCountryModel.iso_3166_1)
        assertEquals(productionCountryEntity.name, productionCountryModel.name)

        val spokenLanguageModel: SpokenLanguage = movieDetailModel.spokenLanguagesList.first()
        assertEquals(spokenLanguageEntity.english_name, spokenLanguageModel.englishName)
        assertEquals(spokenLanguageEntity.iso_639_1, spokenLanguageModel.iso_639_1)
        assertEquals(spokenLanguageEntity.name, spokenLanguageModel.name)
    }

}
