package com.app.jetpack.mvvm.features.upcoming

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.FetchLocalGenreListUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetUpcomingMoviesUseCase
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.GenreToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.MovieItemToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.model.GenreState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

const val DEFAULT_GENRE_ITEM = "All"

@HiltViewModel
class UpComingViewModel @Inject constructor(
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val movieItemToUiStateMapper: MovieItemToUiStateMapper,
    private val fetchLocalGenreListUseCase: FetchLocalGenreListUseCase,
    private val genreToUiStateMapper: GenreToUiStateMapper,
) : ViewModel() {

    var selectedGenre: MutableState<GenreState> =
        mutableStateOf(GenreState(genreId = null, name = DEFAULT_GENRE_ITEM))
    val genreIdData = MutableStateFlow<String?>(null)

    private val _genreStateList: MutableStateFlow<List<GenreState>> = MutableStateFlow(emptyList())
    val genreStateList: StateFlow<List<GenreState>> = _genreStateList

    init {
        fetchGenres()
    }

    fun fetchGenres() {
        viewModelScope.launch {
            _genreStateList.value =
                fetchLocalGenreListUseCase.invoke().map { genreToUiStateMapper.map(it) }
                    .toMutableList().apply {
                        if (this.isNotEmpty() && this.first().name != DEFAULT_GENRE_ITEM) {
                            this.add(0, addGenreState)
                        }
                    }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val upcomingMovies = genreIdData.flatMapLatest { genreId ->
        getUpcomingMoviesUseCase.invoke(genreId.orEmpty()).map { pagingData ->
            pagingData.map { movieItem ->
                movieItemToUiStateMapper.map(movieItem)
            }
        }
    }.cachedIn(viewModelScope)

    companion object {
        private const val DEFAULT_GENRE_ITEM = "All"
        val addGenreState = GenreState(genreId = Int.MAX_VALUE, name = DEFAULT_GENRE_ITEM)
    }
}
