package com.app.jetpack.mvvm.features.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
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
import com.app.jetpack.mvvm.common.domain.models.DataState
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
import kotlinx.coroutines.flow.flow
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

    private val getMovieCreditUseCase: GetMovieCreditUseCase =
        mockk<GetMovieCreditUseCase>(relaxed = true)
    private val getRecommendedMovieUseCase: GetRecommendedMovieUseCase =
        mockk<GetRecommendedMovieUseCase>(relaxed = true)
    private val getMovieDetailUseCase: GetMovieDetailUseCase =
        mockk<GetMovieDetailUseCase>(relaxed = true)
    private val likeMovieUseCase: LikeMovieUseCase =
        mockk<LikeMovieUseCase>(relaxed = true)
    private val unlikeMovieUseCase: UnlikeMovieUseCase =
        mockk<UnlikeMovieUseCase>(relaxed = true)
    private val isMovieFavoriteUseCase: IsMovieFavoriteUseCase =
        mockk<IsMovieFavoriteUseCase>(relaxed = true)
    private val movieDetailToUiStateMapper: MovieDetailToUiStateMapper =
        mockk<MovieDetailToUiStateMapper>(relaxed = true)
    private val artistToUiStateMapper: ArtistToUiStateMapper =
        mockk<ArtistToUiStateMapper>(relaxed = true)
    private val baseModelToUiStateMapper: BaseModelToUiStateMapper =
        mockk<BaseModelToUiStateMapper>(relaxed = true)

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
            val mockDataStateSuccess = mockk<DataState.Success<MovieDetail>>(relaxed = true)
            val mockMovieDetailState = mockk<MovieDetailState>(relaxed = true)

            coEvery { getMovieDetailUseCase.invoke(movieId) } returns flow { mockDataStateSuccess }
            every { mockDataStateSuccess.data } returns mockMovieDetail
            every { movieDetailToUiStateMapper.map(mockMovieDetail) } returns mockMovieDetailState


            // When

            sut.movieDetailApi(movieId)

            // Then
            assertEquals(mockDataStateSuccess, sut.movieDetail.value)

            // Verify mocks
            coVerify { getMovieCreditUseCase.invoke(movieId) }
            coVerify { movieDetailToUiStateMapper.map(mockMovieDetail) }
        }
    }

    @Test
    fun `movieDetailApi fetches movie details with error`() {
        testScope.launch {
            // Given
            val movieId = 123
            val mockException = mockk<Exception>(relaxed = true)
            val mockDataStateError = mockk<DataState.Error<Artist>>(relaxed = true) {
                every { exception } returns mockException
            }
            coEvery { getMovieDetailUseCase.invoke(movieId) } returns flow { mockDataStateError }

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
            val mockDataStateSuccess = mockk<DataState.Success<BaseModel>>(relaxed = true)
            val mockBaseModelState = mockk<BaseModelState>(relaxed = true)

            coEvery { getRecommendedMovieUseCase.invoke(params) } returns flow { mockDataStateSuccess }
            every { mockDataStateSuccess.data } returns mockBaseModel
            every { baseModelToUiStateMapper.map(mockBaseModel) } returns mockBaseModelState


            // When

            sut.recommendedMovieApi(movieId, page)

            // Then
            //val expectedState = DataState.Success(mockBaseModelState)
            assertEquals(mockDataStateSuccess, sut.recommendedMovie.value)

            // Verify mocks
            coVerify { getMovieCreditUseCase.invoke(movieId) }
            coVerify { baseModelToUiStateMapper.map(mockBaseModel) }
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
            val mockDataStateError = mockk<DataState.Error<BaseModel>>(relaxed = true) {
                every { exception } returns mockException
            }

            // Mock dependencies
            coEvery { getRecommendedMovieUseCase.invoke(params) } returns flow { mockDataStateError }

            // When

            sut.movieCredit(movieId)

            // Then
            assertEquals(mockDataStateError, sut.recommendedMovie.value)
            coVerify { getMovieCreditUseCase.invoke(movieId) }
            verifyNoInteractions(artistToUiStateMapper)
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
            val mockArtist = mockk<Artist>(relaxed = true)
            val mockDataStateSuccess = mockk<DataState.Success<Artist>>(relaxed = true)
            val mockArtistState = mockk<ArtistState>(relaxed = true)

            coEvery { getMovieCreditUseCase.invoke(any()) } returns flow { mockDataStateSuccess }
            every { mockDataStateSuccess.data } returns mockArtist
            every { artistToUiStateMapper.map(mockArtist) } returns mockArtistState


            // When

            sut.movieCredit(movieId)

            // Then
            //val expectedState = DataState.Success(mockArtistState)
            assertEquals(mockDataStateSuccess, sut.artist.value)

            // Verify mocks
            coVerify { getMovieCreditUseCase.invoke(movieId) }
            coVerify { artistToUiStateMapper.map(mockArtist) }
        }
    }

    @Test
    fun `movieCredit fetches movie credits with error`() {
        testScope.launch {
            val movieId = 123
            val mockException = mockk<Exception>(relaxed = true)
            val mockDataStateError = mockk<DataState.Error<Artist>>(relaxed = true) {
                every { exception } returns mockException
            }

            // Mock dependencies
            coEvery { getMovieCreditUseCase.invoke(any()) } returns flow { mockDataStateError }

            // When

            sut.movieCredit(movieId)

            // Then
            assertEquals(mockDataStateError, sut.artist.value)
            coVerify { getMovieCreditUseCase.invoke(movieId) }
            verifyNoInteractions(artistToUiStateMapper)
        }
    }


    companion object {

        val belongsToCollection = BelongsToCollection(
            id = 123,
            backdropPath = "backdropPath",
            name = "name",
            posterPath = "posterPath",
        )
        val genre = Genre(
            id = 234,
            name = "name",
        )
        val productionCompany = ProductionCompany(
            id = 234,
            logoPath = "logoPath",
            originCountry = "originCountry",
            name = "name",
        )
        val productionCountry = ProductionCountry(
            isoName = "isoName",
            name = "name",
        )
        val spokenLanguage = SpokenLanguage(
            englishName = "englishName",
            isoName = "isoName",
            name = "name",
        )

        val movieDetail = MovieDetail(
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

