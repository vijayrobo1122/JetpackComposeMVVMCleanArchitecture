package com.app.jetpack.mvvm.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.jetpack.mvvm.business.moviedetail.domain.main.usecase.GetGenresListUseCase
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.GenreToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.model.GenreState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGenresListUseCase: GetGenresListUseCase,
    private val genreToUiStateMapper: GenreToUiStateMapper,
) : ViewModel() {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _genreStateList: MutableStateFlow<List<GenreState>?> = MutableStateFlow(null)
    val genreStateList: StateFlow<List<GenreState>?> = _genreStateList

    fun genreList() {
        viewModelScope.launch {
            _isLoading.value = true
            getGenresListUseCase.invoke().fold(
                onSuccess = { data ->
                    val genresStateList: ArrayList<GenreState> = data.map { genre ->
                        genreToUiStateMapper.map(genre)
                    } as ArrayList<GenreState>
                    if (genresStateList.isNotEmpty() && genresStateList.first().name != DEFAULT_GENRE_ITEM) {
                        genresStateList.add(
                            0,
                            GenreState(genreId = null, name = DEFAULT_GENRE_ITEM)
                        )
                    }
                    _genreStateList.value = genresStateList
                    _isLoading.value = false
                },
                onFailure = {
                    _isLoading.value = false
                },
            )
        }
    }
}
