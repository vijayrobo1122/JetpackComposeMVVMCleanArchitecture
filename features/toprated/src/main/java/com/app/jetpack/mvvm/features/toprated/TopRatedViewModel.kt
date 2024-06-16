package com.app.jetpack.mvvm.features.toprated


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.app.jetpack.mvvm.common.domain.model.GenreId
import com.app.jetpack.mvvm.common.domain.usecase.GetTopRatedMoviesUseCase
import com.app.jetpack.mvvm.common.ui.widgets.mapper.GenreToUiStateMapper
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
class TopRatedViewModel @Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val movieItemToUiStateMapper: MovieItemToUiStateMapper,
    internal val genreToUiStateMapper: GenreToUiStateMapper,
) :
    ViewModel() {
    var selectedGenre: MutableState<GenreState> =
        mutableStateOf(GenreState(genreId = null, name = DEFAULT_GENRE_ITEM))
    val filterData = MutableStateFlow<GenreId?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val topRatedMovies = filterData.flatMapLatest {
        getTopRatedMoviesUseCase.invoke(it?.genreId.orEmpty()).map { pagingData ->
            pagingData.map { movieItem ->
                movieItemToUiStateMapper.map(movieItem)
            }
        }
    }.cachedIn(viewModelScope)
}
