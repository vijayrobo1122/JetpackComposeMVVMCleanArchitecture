package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.GenreEntity
import com.app.jetpack.mvvm.business.moviedetail.data.entity.GenresEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genre
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genres
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class GenresMapperTest {

    private val genreMapper: GenreMapper = mockk(relaxed = true)

    private val sut = GenresMapper(genreMapper = genreMapper)

    @Test
    fun `Given a valid GenresEntity When mapping is done Then a valid Genres should be created`() {

        // Given
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

        val genresEntity = GenresEntity(
            genres = genres
        )


        // When
        val genresModel: Genres = sut.mapTo(genresEntity)

        // Then
        assertEquals(genre.id, genresModel.genres.first().id)
        assertEquals(genre.name, genresModel.genres.first().name)
    }

}