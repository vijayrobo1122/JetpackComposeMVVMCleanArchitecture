package com.app.jetpack.mvvm.features.popular

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetPopularMoviesUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.model.GenreId
import com.app.jetpack.mvvm.common.ui.widgets.mapper.MovieItemToUiStateMapper
import com.app.jetpack.mvvm.common.ui.widgets.model.GenreState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val DEFAULT_GENRE_ITEM = "All"

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val movieItemToUiStateMapper: MovieItemToUiStateMapper,
) :
    ViewModel() {
    var selectedGenre: MutableState<GenreState> =
        mutableStateOf(GenreState(genreId = null, name = DEFAULT_GENRE_ITEM))
    val filterData = MutableStateFlow<GenreId?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val popularMovies = filterData.flatMapLatest {
        getPopularMoviesUseCase.invoke(it?.genreId.orEmpty()).map { pagingData ->
            pagingData.map { movieItem ->
                movieItemToUiStateMapper.map(movieItem)
            }
        }
    }.cachedIn(viewModelScope)
}
