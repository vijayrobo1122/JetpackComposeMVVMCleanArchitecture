package com.app.jetpack.mvvm.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.jetpack.mvvm.common.domain.model.Genres
import com.app.jetpack.mvvm.common.domain.models.DataState
import com.app.jetpack.mvvm.common.domain.usecase.GetGenresListUseCase
import com.app.jetpack.mvvm.common.ui.widgets.mapper.GenreToUiStateMapper
import com.app.jetpack.mvvm.common.ui.widgets.model.GenreState
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

    val genreStateList: MutableState<DataState<List<GenreState>>?> = mutableStateOf(null)

    fun genreList() {
        viewModelScope.launch {
            getGenresListUseCase.invoke().onEach {
                if (it is DataState.Success<Genres>) {
                    genreStateList.value = DataState.Success(it.data.genres.map { genre ->
                        genreToUiStateMapper.map(genre)
                    })
                }
            }.launchIn(viewModelScope)
        }
    }
}
