package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.db.GenreEntity
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genre
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class GenreMapperTest {

    private val sut = GenreMapper()

    @Test
    fun `Given a valid GenreEntity When mapping is done Then a valid Genre should be created`() {

        // Given
        val genreEntity = GenreEntity(
            genreId = 234,
            name = "name",
        )
        val genre = mockk<Genre>(relaxed = true) {
            every { genreId } returns genreEntity.genreId
            every { name } returns genreEntity.name
        }

        // When
        val genreModel: Genre = sut.mapTo(genreEntity)

        // Then
        assertEquals(genre.genreId, genreModel.genreId)
        assertEquals(genre.name, genreModel.name)
    }
}