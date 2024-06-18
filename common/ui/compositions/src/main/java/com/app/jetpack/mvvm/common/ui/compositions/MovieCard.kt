package com.app.jetpack.mvvm.common.ui.compositions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.jetpack.mvvm.common.ui.components.CustomImage
import com.app.jetpack.mvvm.common.ui.theme.cornerRadius
import com.app.jetpack.mvvm.common.ui.widgets.model.MovieItemState

@Composable
fun MovieCard(
    state: MovieItemState,
    onMovieItemClick: (String) -> Unit
) {
    Column(modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 0.dp, bottom = 10.dp)) {

        CustomImage(
            modifier = Modifier
                .size(250.dp)
                .cornerRadius(10.dp)
                .clickable {
                    onMovieItemClick(state.movieId.toString())
                },
            imagePath = BuildConfig.IMAGE_BASE_URL.plus(state.posterPath)
        )
    }
}
