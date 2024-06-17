package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.BaseModelEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.MovieItemEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieItem
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseModelMapperTest {

    private val movieItemMapper: MovieItemMapper = mockk(relaxed = true)
    private val sut = BaseModelMapper(movieItemMapper)

    @Test
    fun `Given a valid BaseModelEntity When mapping is done Then a valid BaseModel should be created`() {

        // Given
        val movieItemEntity = MovieItemEntity(
            id = 123,
            adult = true,
            backdropPath = "backdropPath",
            genreIds = listOf(1, 3),
            originalLanguage = "originalLanguage",
            originalTitle = "originalTitle",
            overview = "overview",
            popularity = 8.5,
            posterPath = "posterPath",
            releaseDate = "releaseDate",
            title = "title",
            video = true,
            voteAverage = 15.67,
            voteCount = 89,
        )
        val movieItem = mockk<MovieItem>(relaxed = true) {
            every { id } returns movieItemEntity.id
            every { isAdult } returns movieItemEntity.adult
            every { backdropPath } returns movieItemEntity.backdropPath
            every { genreIdsList } returns movieItemEntity.genreIds
            every { originalLanguage } returns movieItemEntity.originalLanguage
            every { originalTitle } returns movieItemEntity.originalTitle
            every { overview } returns movieItemEntity.overview
            every { popularity } returns movieItemEntity.popularity
            every { posterPath } returns movieItemEntity.posterPath
            every { releaseDate } returns movieItemEntity.releaseDate
            every { title } returns movieItemEntity.title
            every { isVideo } returns movieItemEntity.video
            every { voteAverage } returns movieItemEntity.voteAverage
            every { voteCount } returns movieItemEntity.voteCount
        }
        every { movieItemMapper.mapTo(movieItemEntity) } returns movieItem
        val movieItems = listOf(movieItemEntity)

        val baseModelEntity = BaseModelEntity(
            page = 2,
            totalPages = 10,
            totalResults = 23,
            results = movieItems
        )

        // When
        val baseModel = sut.mapTo(baseModelEntity)

        // Then
        assertEquals(baseModelEntity.page, baseModel.page)
        assertEquals(baseModelEntity.totalPages, baseModel.totalPages)
        assertEquals(baseModelEntity.totalResults, baseModel.totalResults)

        val movieItemModel = baseModel.moviesList.first()
        assertEquals(movieItemEntity.id, movieItemModel.id)
        assertEquals(movieItemEntity.adult, movieItemModel.isAdult)
        assertEquals(movieItemEntity.backdropPath, movieItemModel.backdropPath)
        assertEquals(movieItemEntity.genreIds, movieItemModel.genreIdsList)
        assertEquals(movieItemEntity.originalLanguage, movieItemModel.originalLanguage)
        assertEquals(movieItemEntity.originalTitle, movieItemModel.originalTitle)
        assertEquals(movieItemEntity.overview, movieItemModel.overview)
        assertEquals(movieItemEntity.posterPath, movieItemModel.posterPath)
        assertEquals(movieItemEntity.releaseDate, movieItemModel.releaseDate)
        assertEquals(movieItemEntity.title, movieItemModel.title)
        assertEquals(movieItemEntity.video, movieItemModel.isVideo)
        assertEquals(movieItemEntity.voteCount, movieItemModel.voteCount)

    }
}
