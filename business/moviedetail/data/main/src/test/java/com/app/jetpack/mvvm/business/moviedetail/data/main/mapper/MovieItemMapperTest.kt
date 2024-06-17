package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.MovieItemEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieItemMapperTest {

    private val sut = MovieItemMapper()

    @Test
    fun `Given a valid MovieItemEntity When mapping is done Then a valid MovieItem should be created`() {

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

        // When
        val movieItemModel = sut.mapTo(movieItemEntity)

        // Then
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
