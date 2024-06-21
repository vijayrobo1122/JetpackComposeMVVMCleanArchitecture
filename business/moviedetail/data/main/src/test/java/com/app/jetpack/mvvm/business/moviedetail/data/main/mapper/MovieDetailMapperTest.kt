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
            backdropPath = "backdropPath",
            name = "name",
            posterPath = "posterPath",
        )
        val belongsToCollection = mockk<BelongsToCollection>(relaxed = true) {
            every { id } returns belongsToCollectionEntity.id
            every { backdropPath } returns belongsToCollectionEntity.backdropPath
            every { name } returns belongsToCollectionEntity.name
            every { posterPath } returns belongsToCollectionEntity.posterPath
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
            logoPath = "logoPath",
            originCountry = "originCountry",
            name = "name",
        )
        val productionCompany = mockk<ProductionCompany>(relaxed = true) {
            every { id } returns productionCompanyEntity.id
            every { logoPath } returns productionCompanyEntity.logoPath
            every { originCountry } returns productionCompanyEntity.originCountry
            every { name } returns productionCompanyEntity.name
        }
        every { productionCompanyMapper.mapTo(productionCompanyEntity) } returns productionCompany
        val productionCompanies = listOf(productionCompanyEntity)


        val productionCountryEntity = ProductionCountryEntity(
            isoName = "isoName",
            name = "name",
        )
        val productionCountry = mockk<ProductionCountry>(relaxed = true) {
            every { isoName } returns productionCountryEntity.isoName
            every { name } returns productionCountryEntity.name
        }
        every { productionCountryMapper.mapTo(productionCountryEntity) } returns productionCountry
        val productionCountries = listOf(productionCountryEntity)

        val spokenLanguageEntity = SpokenLanguageEntity(
            englishName = "englishName",
            isoName = "isoName",
            name = "name",
        )
        val spokenLanguage = mockk<SpokenLanguage>(relaxed = true) {
            every { englishName } returns spokenLanguageEntity.englishName
            every { isoName } returns spokenLanguageEntity.isoName
            every { name } returns spokenLanguageEntity.name
        }
        every { spokenLanguageMapper.mapTo(spokenLanguageEntity) } returns spokenLanguage
        val spokenLanguages = listOf(spokenLanguageEntity)


        val movieDetailEntity = MovieDetailEntity(
            id = 123,
            isAdult = true,
            backdropPath = "backdropPath",
            belongsToCollection = belongsToCollectionEntity,
            budget = 85,
            genresList = genres,
            homepage = "homepage",
            imdbId = "imdbId",
            originalLanguage = "originalLanguage",
            originalTitle = "originalTitle",
            overview = "overview",
            popularity = 15.67,
            posterPath = "posterPath",
            productionCompaniesList = productionCompanies,
            productionCountriesList = productionCountries,
            releaseDate = "releaseDate",
            revenue = 9,
            runtime = 923,
            spokenLanguagesList = spokenLanguages,
            status = "status",
            tagline = "tagline",
            title = "title",
            isVideo = true,
            isFavorite = false,
            voteAverage = 89.23,
            voteCount = 89,
        )


        // When
        val movieDetailModel = sut.mapTo(movieDetailEntity)

        // Then


        assertEquals(movieDetailEntity.id, movieDetailModel.id)
        assertEquals(movieDetailEntity.isAdult, movieDetailModel.isAdult)
        assertEquals(movieDetailEntity.backdropPath, movieDetailModel.backdropPath)
        assertEquals(movieDetailEntity.budget, movieDetailModel.budget)
        assertEquals(movieDetailEntity.homepage, movieDetailModel.homepage)
        assertEquals(movieDetailEntity.imdbId, movieDetailModel.imdbId)
        assertEquals(movieDetailEntity.originalLanguage, movieDetailModel.originalLanguage)
        assertEquals(movieDetailEntity.originalTitle, movieDetailModel.originalTitle)
        assertEquals(movieDetailEntity.overview, movieDetailModel.overview)
        assertEquals(movieDetailEntity.posterPath, movieDetailModel.posterPath)
        assertEquals(movieDetailEntity.releaseDate, movieDetailModel.releaseDate)
        assertEquals(movieDetailEntity.revenue, movieDetailModel.revenue)
        assertEquals(movieDetailEntity.runtime, movieDetailModel.runtime)
        assertEquals(movieDetailEntity.status, movieDetailModel.status)
        assertEquals(movieDetailEntity.tagline, movieDetailModel.tagline)
        assertEquals(movieDetailEntity.title, movieDetailModel.title)
        assertEquals(movieDetailEntity.isVideo, movieDetailModel.isVideo)
        assertEquals(movieDetailEntity.isFavorite, movieDetailModel.isFavorite)
        assertEquals(movieDetailEntity.voteCount, movieDetailModel.voteCount)


        val belongsToCollectionModel = movieDetailModel.belongsToCollection
        assertEquals(belongsToCollectionEntity.id, belongsToCollectionModel.id)
        assertEquals(belongsToCollectionEntity.backdropPath, belongsToCollectionModel.backdropPath)
        assertEquals(belongsToCollectionEntity.name, belongsToCollectionModel.name)
        assertEquals(belongsToCollectionEntity.posterPath, belongsToCollectionModel.posterPath)

        val genreModel = movieDetailModel.genresList.first()
        assertEquals(genre.id, genreModel.id)
        assertEquals(genre.name, genreModel.name)

        val productionCompanyModel = movieDetailModel.productionCompaniesList.first()
        assertEquals(productionCompanyEntity.id, productionCompanyModel.id)
        assertEquals(productionCompanyEntity.logoPath, productionCompanyModel.logoPath)
        assertEquals(productionCompanyEntity.originCountry, productionCompanyModel.originCountry)
        assertEquals(productionCompanyEntity.name, productionCompanyModel.name)

        val productionCountryModel = movieDetailModel.productionCountriesList.first()
        assertEquals(productionCountryEntity.isoName, productionCountryModel.isoName)
        assertEquals(productionCountryEntity.name, productionCountryModel.name)

        val spokenLanguageModel: SpokenLanguage = movieDetailModel.spokenLanguagesList.first()
        assertEquals(spokenLanguageEntity.englishName, spokenLanguageModel.englishName)
        assertEquals(spokenLanguageEntity.isoName, spokenLanguageModel.isoName)
        assertEquals(spokenLanguageEntity.name, spokenLanguageModel.name)
    }

}
