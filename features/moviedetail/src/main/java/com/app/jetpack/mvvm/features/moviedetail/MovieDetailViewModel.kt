package com.app.jetpack.mvvm.features.moviedetail


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase.GetMovieCreditUseCase
import com.app.jetpack.mvvm.business.artistdetail.domain.model.Artist
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetMovieDetailUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetRecommendedMovieUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.model.BaseModel
import com.app.jetpack.mvvm.business.moviedetail.domain.model.MovieDetail
import com.app.jetpack.mvvm.common.domain.models.DataState
import com.app.jetpack.mvvm.common.ui.widgets.mapper.ArtistToUiStateMapper
import com.app.jetpack.mvvm.common.ui.widgets.mapper.BaseModelToUiStateMapper
import com.app.jetpack.mvvm.common.ui.widgets.mapper.MovieDetailToUiStateMapper
import com.app.jetpack.mvvm.common.ui.widgets.model.ArtistState
import com.app.jetpack.mvvm.common.ui.widgets.model.BaseModelState
import com.app.jetpack.mvvm.common.ui.widgets.model.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieCreditUseCase: GetMovieCreditUseCase,
    private val getRecommendedMovieUseCase: GetRecommendedMovieUseCase,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val movieDetailToUiStateMapper: MovieDetailToUiStateMapper,
    private val artistToUiStateMapper: ArtistToUiStateMapper,
    private val baseModelToUiStateMapper: BaseModelToUiStateMapper,
) : ViewModel() {
    val movieDetail: MutableState<DataState<MovieDetailState>?> = mutableStateOf(null)
    val recommendedMovie: MutableState<DataState<BaseModelState>?> = mutableStateOf(null)
    val artist: MutableState<DataState<ArtistState>?> = mutableStateOf(null)

    fun movieDetailApi(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailUseCase.invoke(movieId).onEach {
                if (it is DataState.Success<MovieDetail>) {
                    movieDetail.value = DataState.Success(movieDetailToUiStateMapper.map(it.data))
                }
            }.launchIn(viewModelScope)
        }
    }

    fun recommendedMovieApi(movieId: Int, page: Int) {
        viewModelScope.launch {
            getRecommendedMovieUseCase.invoke(GetRecommendedMovieUseCase.Params(movieId, page))
                .onEach {
                    if (it is DataState.Success<BaseModel>) {
                        recommendedMovie.value =
                            DataState.Success(baseModelToUiStateMapper.map(it.data))
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun movieCredit(movieId: Int) {
        viewModelScope.launch {
            getMovieCreditUseCase.invoke(movieId).onEach {
                if (it is DataState.Success<Artist>) {
                    artist.value = DataState.Success(artistToUiStateMapper.map(it.data))
                }
            }.launchIn(viewModelScope)
        }
    }

}
