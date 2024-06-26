package com.app.jetpack.mvvm.features.moviedetail


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase.GetMovieCreditUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetMovieDetailUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetRecommendedMovieUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.IsMovieFavoriteUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.LikeMovieUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.UnlikeMovieUseCase
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.ArtistToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.BaseModelToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.MovieDetailToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.model.ArtistState
import com.app.jetpack.mvvm.common.presentation.widgets.model.BaseModelState
import com.app.jetpack.mvvm.common.presentation.widgets.model.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressWarnings("LongParameterList")
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieCreditUseCase: GetMovieCreditUseCase,
    private val getRecommendedMovieUseCase: GetRecommendedMovieUseCase,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val likeMovieUseCase: LikeMovieUseCase,
    private val unlikeMovieUseCase: UnlikeMovieUseCase,
    private val isMovieFavoriteUseCase: IsMovieFavoriteUseCase,
    private val movieDetailToUiStateMapper: MovieDetailToUiStateMapper,
    private val artistToUiStateMapper: ArtistToUiStateMapper,
    private val baseModelToUiStateMapper: BaseModelToUiStateMapper,
) : ViewModel() {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _artist: MutableStateFlow<ArtistState?> = MutableStateFlow(null)
    val artist: StateFlow<ArtistState?> = _artist

    private val _movieDetail: MutableStateFlow<MovieDetailState?> = MutableStateFlow(null)
    val movieDetail: StateFlow<MovieDetailState?> = _movieDetail

    private val _recommendedMovie: MutableStateFlow<BaseModelState?> = MutableStateFlow(null)
    val recommendedMovie: StateFlow<BaseModelState?> = _recommendedMovie

    fun movieDetailApi(movieId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            getMovieDetailUseCase.invoke(movieId).fold(
                onSuccess = { data ->
                    _movieDetail.value = movieDetailToUiStateMapper.map(data).also {
                        it.isFavorite = isMovieFavoriteUseCase.invoke(movieId)
                    }
                    _isLoading.value = false
                },
                onFailure = {
                    _isLoading.value = false
                },
            )
        }
    }

    fun recommendedMovieApi(movieId: Int, page: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            getRecommendedMovieUseCase.invoke(GetRecommendedMovieUseCase.Params(movieId, page))
                .fold(
                    onSuccess = { data ->
                        _recommendedMovie.value = baseModelToUiStateMapper.map(data)
                        _isLoading.value = false
                    },
                    onFailure = {
                        _isLoading.value = false
                    },
                )
        }
    }

    fun movieCredit(movieId: Int) {
        viewModelScope.launch {
            getMovieCreditUseCase.invoke(movieId).fold(
                onSuccess = { data ->
                    _artist.value = artistToUiStateMapper.map(data)
                },
                onFailure = {

                }
            )
        }
    }

    fun toggleFavorite(movieId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                likeMovieUseCase.invoke(movieId)
            } else {
                unlikeMovieUseCase.invoke(movieId)
            }
        }
    }
}
