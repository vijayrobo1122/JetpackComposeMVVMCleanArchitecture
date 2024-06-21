package com.app.jetpack.mvvm.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetGenresListUseCase
import com.app.jetpack.mvvm.business.moviedetail.domain.model.Genres
import com.app.jetpack.mvvm.common.domain.models.DataState
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.GenreToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.model.GenreState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGenresListUseCase: GetGenresListUseCase,
    private val genreToUiStateMapper: GenreToUiStateMapper,
) : ViewModel() {

    val genreStateList: MutableState<List<GenreState>> = mutableStateOf(arrayListOf())

    fun genreList() {
        viewModelScope.launch {
            getGenresListUseCase.invoke().onEach {
                if (it is DataState.Success<Genres>) {
                    val genresStateList: ArrayList<GenreState> = it.data.genres.map { genre ->
                        genreToUiStateMapper.map(genre)
                    } as ArrayList<GenreState>
                    if (genresStateList.first().name != DEFAULT_GENRE_ITEM) {
                        genresStateList.add(
                            0,
                            GenreState(genreId = null, name = DEFAULT_GENRE_ITEM)
                        )
                    }
                    genreStateList.value = genresStateList
                }
            }.launchIn(viewModelScope)
        }
    }
}
