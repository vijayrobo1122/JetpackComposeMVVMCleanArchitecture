package com.app.jetpack.mvvm.features.artistdetail


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.jetpack.mvvm.common.ui.compositions.ArtistDetailWidget
import com.app.jetpack.mvvm.common.ui.compositions.CircularIndeterminateProgressBar
import com.app.jetpack.mvvm.common.ui.theme.DefaultBackgroundColor

@Composable
fun ArtistDetailScreen(artistId: Int) {
    val viewModel = hiltViewModel<ArtistDetailViewModel>()
    val artistDetail = viewModel.artistDetail.collectAsState()
    val progressBar = remember { mutableStateOf(false) }
    val isLoading by viewModel.isLoading

    LaunchedEffect(true) {
        viewModel.artistDetail(artistId)
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

        CircularIndeterminateProgressBar(isDisplayed = progressBar.value)

        artistDetail.value?.let {
            ArtistDetailWidget(
                modifier = Modifier,
                artistDetailState = it
            )
        }
    }

    progressBar.value = isLoading
}
