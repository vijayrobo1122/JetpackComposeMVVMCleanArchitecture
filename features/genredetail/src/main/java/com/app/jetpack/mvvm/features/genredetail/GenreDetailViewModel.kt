package com.app.jetpack.mvvm.features.genredetail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetGenreMoviesUseCase
import com.app.jetpack.mvvm.common.ui.widgets.mapper.MovieItemToUiStateMapper
import com.app.jetpack.mvvm.common.ui.widgets.model.MovieItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class GenreDetailViewModel @Inject constructor(
    private val getGenreMoviesUseCase: GetGenreMoviesUseCase,
    private val movieItemToUiStateMapper: MovieItemToUiStateMapper,
) : ViewModel() {


    fun moviesByGenre(genreId: String): Flow<PagingData<MovieItemState>> {
        return getGenreMoviesUseCase.invoke(genreId).map { pagingData ->
            pagingData.map { movieItem ->
                movieItemToUiStateMapper.map(movieItem)
            }
        }.cachedIn(viewModelScope)
    }
}
