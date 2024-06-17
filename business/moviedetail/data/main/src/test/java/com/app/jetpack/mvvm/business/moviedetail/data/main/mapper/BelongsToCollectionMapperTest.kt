package com.app.jetpack.mvvm.business.moviedetail.data.main.mapper

import com.app.jetpack.mvvm.business.moviedetail.data.entity.BelongsToCollectionEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class BelongsToCollectionMapperTest {

    private val sut = BelongsToCollectionMapper()

    @Test
    fun `Given a valid BelongsToCollectionEntity When mapping is done Then a valid BelongsToCollection should be created`() {

        // Given
        val belongsToCollectionEntity = BelongsToCollectionEntity(
            id = 123,
            backdrop_path = "backdrop_path",
            name = "name",
            poster_path = "poster_path",
        )

        // When
        val belongsToCollectionModel = sut.mapTo(belongsToCollectionEntity)

        // Then
        assertEquals(belongsToCollectionEntity.id, belongsToCollectionModel.id)
        assertEquals(belongsToCollectionEntity.backdrop_path, belongsToCollectionModel.backdropPath)
        assertEquals(belongsToCollectionEntity.name, belongsToCollectionModel.name)
        assertEquals(belongsToCollectionEntity.poster_path, belongsToCollectionModel.posterPath)
    }
}
