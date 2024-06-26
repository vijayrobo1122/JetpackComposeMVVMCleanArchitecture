package com.app.jetpack.mvvm.features.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import app.cash.turbine.test
import com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase.GetMovieCreditUseCase
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Artist
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetMovieDetailUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetRecommendedMovieUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.IsMovieFavoriteUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.LikeMovieUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.UnlikeMovieUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BaseModel
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BelongsToCollection
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genre
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieDetail
import com.app.jetpack.mvvm.business.moviedetail.domain.model.ProductionCompany
import com.app.jetpack.mvvm.business.moviedetail.domain.model.ProductionCountry
import com.app.jetpack.mvvm.business.moviedetail.domain.model.SpokenLanguage
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.ArtistToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.BaseModelToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.MovieDetailToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.model.ArtistState
import com.app.jetpack.mvvm.common.presentation.widgets.model.BaseModelState
import com.app.jetpack.mvvm.common.presentation.widgets.model.MovieDetailState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val getMovieCreditUseCase = mockk<GetMovieCreditUseCase>(relaxed = true)
    private val getRecommendedMovieUseCase = mockk<GetRecommendedMovieUseCase>(relaxed = true)
    private val getMovieDetailUseCase = mockk<GetMovieDetailUseCase>(relaxed = true)
    private val likeMovieUseCase = mockk<LikeMovieUseCase>(relaxed = true)
    private val unlikeMovieUseCase = mockk<UnlikeMovieUseCase>(relaxed = true)
    private val isMovieFavoriteUseCase = mockk<IsMovieFavoriteUseCase>(relaxed = true)
    private val movieDetailToUiStateMapper = mockk<MovieDetailToUiStateMapper>(relaxed = true)
    private val artistToUiStateMapper = mockk<ArtistToUiStateMapper>(relaxed = true)
    private val baseModelToUiStateMapper = mockk<BaseModelToUiStateMapper>(relaxed = true)

    private lateinit var sut: MovieDetailViewModel

    private val testDispatcher = StandardTestDispatcher()

    private val testScope = TestScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sut = MovieDetailViewModel(
            getMovieCreditUseCase,
            getRecommendedMovieUseCase,
            getMovieDetailUseCase,
            likeMovieUseCase,
            unlikeMovieUseCase,
            isMovieFavoriteUseCase,
            movieDetailToUiStateMapper,
            artistToUiStateMapper,
            baseModelToUiStateMapper
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test toggleFavorite when isFavorite is true`() = runTest {
        testScope.launch {
            //Given
            val movieId = 1
            coEvery { likeMovieUseCase.invoke(movieId) } returns Unit

            //When
            sut.toggleFavorite(movieId, true)

            //Then
            coVerify { likeMovieUseCase.invoke(movieId) }
        }
    }

    @Test
    fun `test toggleFavorite when isFavorite is false`() = runTest {
        testScope.launch {
            //Given
            val movieId = 1
            coEvery { unlikeMovieUseCase.invoke(movieId) } returns Unit

            //When
            sut.toggleFavorite(movieId, false)

            //Then
            coVerify { unlikeMovieUseCase.invoke(movieId) }
        }
    }

    @Test
    fun `movieDetailApi initial state is null`() {

        assertEquals(null, sut.movieDetail.value)
    }

    @Test
    fun `movieDetailApi fetches movie details successfully`() {

        testScope.launch {
            // Given
            val movieId = 123
            val mockMovieDetail = mockk<MovieDetail>(relaxed = true)
            val mockMovieDetailState = mockk<MovieDetailState>(relaxed = true)
            val mockDataStateSuccess = Result.success(mockMovieDetail)
            coEvery { isMovieFavoriteUseCase.invoke(movieId) } returns false
            coEvery { getMovieDetailUseCase.invoke(movieId) } returns mockDataStateSuccess
            every { movieDetailToUiStateMapper.map(mockMovieDetail) } returns mockMovieDetailState


            // When
            sut.movieDetailApi(movieId)

            // Then
            coVerify { getMovieCreditUseCase.invoke(movieId) }
            coVerify { isMovieFavoriteUseCase.invoke(movieId) }
            coVerify { movieDetailToUiStateMapper.map(mockMovieDetail) }
            sut.movieDetail.test {
                val item = awaitItem()
                assertEquals(mockDataStateSuccess.getOrNull(), item)
                assertEquals(false, item?.isFavorite)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `movieDetailApi fetches movie details with favorite status successfully`() {

        testScope.launch {
            // Given
            val movieId = 123
            val mockMovieDetail = mockk<MovieDetail>(relaxed = true)
            val mockMovieDetailState = mockk<MovieDetailState>(relaxed = true)
            val mockDataStateSuccess = Result.success(mockMovieDetail)
            coEvery { isMovieFavoriteUseCase.invoke(movieId) } returns true
            coEvery { getMovieDetailUseCase.invoke(movieId) } returns mockDataStateSuccess
            every { movieDetailToUiStateMapper.map(mockMovieDetail) } returns mockMovieDetailState


            // When
            sut.movieDetailApi(movieId)

            // Then
            coVerify { getMovieCreditUseCase.invoke(movieId) }
            coVerify { isMovieFavoriteUseCase.invoke(movieId) }
            coVerify { movieDetailToUiStateMapper.map(mockMovieDetail) }
            sut.movieDetail.test {
                val item = awaitItem()
                assertEquals(mockDataStateSuccess.getOrNull(), item)
                assertEquals(true, item?.isFavorite)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `movieDetailApi fetches movie details with error`() {
        testScope.launch {
            // Given
            val movieId = 123
            val mockException = mockk<Exception>(relaxed = true)
            val mockDataStateError = Result.failure<MovieDetail>(mockException)
            coEvery { getMovieDetailUseCase.invoke(movieId) } returns mockDataStateError

            // When
            sut.movieDetailApi(movieId)

            // Then
            assertEquals(mockDataStateError, sut.recommendedMovie.value)
            coVerify { getMovieDetailUseCase.invoke(movieId) }
            verifyNoInteractions(movieDetailToUiStateMapper)
        }
    }

    @Test
    fun `recommendedMovie initial state is null`() {

        assertEquals(null, sut.recommendedMovie.value)
    }

    @Test
    fun `recommendedMovieApi fetches recommended movies successfully`() {

        testScope.launch {
            // Given
            val movieId = 123
            val page = 1
            val params = GetRecommendedMovieUseCase.Params(movieId, page)
            val mockBaseModel = mockk<BaseModel>(relaxed = true)
            val mockBaseModelState = mockk<BaseModelState>(relaxed = true)
            val mockDataStateSuccess = Result.success(mockBaseModel)
            coEvery { getRecommendedMovieUseCase.invoke(params) } returns mockDataStateSuccess
            every { baseModelToUiStateMapper.map(mockBaseModel) } returns mockBaseModelState


            // When
            sut.recommendedMovieApi(movieId, page)

            // Then
            coVerify { getMovieCreditUseCase.invoke(movieId) }
            coVerify { baseModelToUiStateMapper.map(mockBaseModel) }
            sut.recommendedMovie.test {
                val item = awaitItem()
                assertEquals(mockDataStateSuccess.getOrNull(), item)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `recommendedMovieApi fetches recommended movies with error`() {
        testScope.launch {
            // Given
            val movieId = 123
            val page = 1
            val params = GetRecommendedMovieUseCase.Params(movieId, page)
            val mockException = mockk<Exception>(relaxed = true)
            val mockDataStateError = Result.failure<BaseModel>(mockException)
            coEvery { getRecommendedMovieUseCase.invoke(params) } returns mockDataStateError

            // When
            sut.movieCredit(movieId)

            // Then
            coVerify { getMovieCreditUseCase.invoke(movieId) }
            verifyNoInteractions(artistToUiStateMapper)
            assertEquals(mockDataStateError, sut.recommendedMovie.value)
        }
    }


    @Test
    fun `artist initial state is null`() {

        assertEquals(null, sut.artist.value)
    }

    @Test
    fun `movieCredit fetches movie credits successfully`() {

        testScope.launch {
            // Given
            val movieId = 123
            val mockArtistState = mockk<ArtistState>(relaxed = true)
            val mockArtist = mockk<Artist>(relaxed = true)
            val mockDataStateSuccess = Result.success(mockArtist)
            coEvery { getMovieCreditUseCase.invoke(any()) } returns mockDataStateSuccess
            every { artistToUiStateMapper.map(mockArtist) } returns mockArtistState

            // When
            sut.movieCredit(movieId)

            // Then
            coVerify { getMovieCreditUseCase.invoke(movieId) }
            coVerify { artistToUiStateMapper.map(mockArtist) }
            sut.artist.test {
                val item = awaitItem()
                assertEquals(mockDataStateSuccess.getOrNull(), item)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `movieCredit fetches movie credits with error`() {
        testScope.launch {
            val movieId = 123
            val mockException = mockk<Exception>(relaxed = true)
            val mockDataStateError = Result.failure<Artist>(mockException)
            coEvery { getMovieCreditUseCase.invoke(any()) } returns mockDataStateError

            // When
            sut.movieCredit(movieId)

            // Then
            coVerify { getMovieCreditUseCase.invoke(movieId) }
            verifyNoInteractions(artistToUiStateMapper)
            sut.artist.test {
                val item = awaitItem()
                assertEquals(mockDataStateError, item)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }


    companion object {

        private val belongsToCollection = BelongsToCollection(
            id = 123,
            backdropPath = "backdropPath",
            name = "name",
            posterPath = "posterPath",
        )
        private val genre = Genre(
            genreId = 234,
            name = "name",
        )
        private val productionCompany = ProductionCompany(
            id = 234,
            logoPath = "logoPath",
            originCountry = "originCountry",
            name = "name",
        )
        private val productionCountry = ProductionCountry(
            isoName = "isoName",
            name = "name",
        )
        private val spokenLanguage = SpokenLanguage(
            englishName = "englishName",
            isoName = "isoName",
            name = "name",
        )

        private val movieDetail = MovieDetail(
            id = 123,
            isAdult = true,
            backdropPath = "backdropPath",
            belongsToCollection = belongsToCollection,
            budget = 85,
            genresList = listOf(genre),
            homepage = "homepage",
            imdbId = "imdbId",
            originalLanguage = "originalLanguage",
            originalTitle = "originalTitle",
            overview = "overview",
            popularity = 15.67,
            posterPath = "posterPath",
            productionCompaniesList = listOf(productionCompany),
            productionCountriesList = listOf(productionCountry),
            releaseDate = "releaseDate",
            revenue = 9,
            runtime = 923,
            spokenLanguagesList = listOf(spokenLanguage),
            status = "status",
            tagline = "tagline",
            title = "title",
            isVideo = true,
            isFavorite = false,
            voteAverage = 89.23,
            voteCount = 89,
        )
    }
}

fun <T> LiveData<T>.observeForeverMock(): Observer<T> {
    val observer: Observer<T> = mockk(relaxed = true)
    this.observeForever(observer)
    return observer
}

