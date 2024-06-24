package com.app.jetpack.mvvm.features.artistdetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase.GetArtistDetailUseCase
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.ArtistDetailToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.model.ArtistDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    private val getArtistDetailUseCase: GetArtistDetailUseCase,
    private val artistDetailToUiStateMapper: ArtistDetailToUiStateMapper,
) : ViewModel() {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _artistDetail: MutableStateFlow<ArtistDetailState?> = MutableStateFlow(null)
    val artistDetail: StateFlow<ArtistDetailState?> = _artistDetail


    fun artistDetail(artistId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            getArtistDetailUseCase(artistId).fold(
                onSuccess = { data ->
                    _artistDetail.value = artistDetailToUiStateMapper.map(data)
                    _isLoading.value = false
                },
                onFailure = {
                    _isLoading.value = false
                },
            )
            /*getArtistDetailUseCase.invoke(artistId).onEach {
                if (it is DataState.Success<ArtistDetail>) {
                    artistDetail.value = DataState.Success(artistDetailToUiStateMapper.map(it.data))
                }
            }.launchIn(viewModelScope)*/
        }
    }
}
