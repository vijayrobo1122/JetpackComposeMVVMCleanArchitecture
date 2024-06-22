package com.app.jetpack.mvvm.features.artistdetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.jetpack.mvvm.business.artistdetail.domain.main.usecase.GetArtistDetailUseCase
import com.app.jetpack.mvvm.business.artistdetail.domain.model.ArtistDetail
import com.app.jetpack.mvvm.common.domain.models.DataState
import com.app.jetpack.mvvm.common.presentation.widgets.mapper.ArtistDetailToUiStateMapper
import com.app.jetpack.mvvm.common.presentation.widgets.model.ArtistDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    private val getArtistDetailUseCase: GetArtistDetailUseCase,
    private val artistDetailToUiStateMapper: ArtistDetailToUiStateMapper,
) : ViewModel() {
    val artistDetail: MutableState<DataState<ArtistDetailState>?> = mutableStateOf(null)

    fun artistDetail(artistId: Int) {
        viewModelScope.launch {
            getArtistDetailUseCase.invoke(artistId).onEach {
                if (it is DataState.Success<ArtistDetail>) {
                    artistDetail.value = DataState.Success(artistDetailToUiStateMapper.map(it.data))
                }
            }.launchIn(viewModelScope)
        }
    }
}
