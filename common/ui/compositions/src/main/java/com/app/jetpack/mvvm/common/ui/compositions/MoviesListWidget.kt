package com.app.jetpack.mvvm.common.ui.compositions


import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.jetpack.mvvm.common.general.extensions.conditional
import com.app.jetpack.mvvm.common.general.extensions.items
import com.app.jetpack.mvvm.common.general.extensions.pagingLoadingState
import com.app.jetpack.mvvm.common.ui.theme.DefaultBackgroundColor
import com.app.jetpack.mvvm.common.ui.widgets.model.GenreState
import com.app.jetpack.mvvm.common.ui.widgets.model.MovieItemState
import kotlinx.coroutines.flow.Flow

@SuppressWarnings("LongParameterList")
@Composable
fun MoviesListWidget(
    isShowExitAppDialog: Boolean = false,
    movies: Flow<PagingData<MovieItemState>>,
    genresStateList: List<GenreState>? = null,
    selectedGenreState: GenreState?,
    onMovieItemClick: (String) -> Unit,
    onGenreItemClick: (genreState: GenreState?) -> Unit,
) {
    val activity = (LocalContext.current as? Activity)
    val progressBar = remember { mutableStateOf(false) }
    val openDialog = remember { mutableStateOf(false) }
    val moviesItems: LazyPagingItems<MovieItemState> = movies.collectAsLazyPagingItems()

    BackHandler(enabled = isShowExitAppDialog) {
        openDialog.value = true
    }
    Column(modifier = Modifier.background(DefaultBackgroundColor)) {
        genresStateList?.let {
            LazyRow(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 9.dp, end = 9.dp)
                    .fillMaxWidth()
            ) {
                items(genresStateList) { item ->
                    SelectableGenreChip(
                        selected = item.name === selectedGenreState?.name,
                        genre = item.name,
                        onclick = {
                            onGenreItemClick(item)
                        }
                    )
                }
            }
        }
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value)
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp)
                .conditional(genresStateList == null) {
                    padding(top = 8.dp)
                },
            content = {
                items(moviesItems) { item ->
                    item?.let {
                        MovieCard(item, onMovieItemClick)
                    }
                }
            })
    }
    if (openDialog.value && isShowExitAppDialog) {
        ExitAlertDialog(
            cancelButtonClick = {
                openDialog.value = it
            }, okButtonClick = {
                activity?.finish()
            })

    }
    moviesItems.pagingLoadingState {
        progressBar.value = it
    }
}
