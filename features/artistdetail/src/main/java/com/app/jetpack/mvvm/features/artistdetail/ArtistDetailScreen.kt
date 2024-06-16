package com.app.jetpack.mvvm.features.artistdetail


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.jetpack.mvvm.common.domain.DataState
import com.app.jetpack.mvvm.common.domain.pagingLoadingState
import com.app.jetpack.mvvm.common.ui.compositions.ArtistDetailWidget
import com.app.jetpack.mvvm.common.ui.compositions.CircularIndeterminateProgressBar
import com.app.jetpack.mvvm.common.ui.theme.DefaultBackgroundColor
import com.app.jetpack.mvvm.common.ui.widgets.model.ArtistDetailState

@Composable
fun ArtistDetailScreen(personId: Int) {
    val artistDetailViewModel = hiltViewModel<ArtistDetailViewModel>()
    val artistDetail = artistDetailViewModel.artistDetail
    val progressBar = remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        artistDetailViewModel.artistDetail(personId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                DefaultBackgroundColor
            )
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
    ) {

        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)

        artistDetail.value.let {
            if (it is DataState.Success<ArtistDetailState>) {
                ArtistDetailWidget(
                    modifier = Modifier,
                    artistDetailState = it.data
                )
            }
        }
    }

    artistDetail.pagingLoadingState {
        progressBar.value = it
    }
}
