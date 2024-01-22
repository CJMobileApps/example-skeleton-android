package com.cjmobileapps.exampleskeletonandroid.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cjmobileapps.exampleskeletonandroid.R
import com.cjmobileapps.exampleskeletonandroid.data.model.Post
import com.cjmobileapps.exampleskeletonandroid.ui.detail.viewmodel.DetailViewModel

@Composable
fun DetailUi(
    detailViewModel: DetailViewModel
) {
    Scaffold { innerPadding ->
        when (val state = detailViewModel.getState()) {
            is DetailViewModel.DetailState.Idle -> { }
            is DetailViewModel.DetailState.DetailLoadedState -> {
                DetailLoadedUi(
                    modifier = Modifier.padding(innerPadding),
                    post = state.post
                )
            }
        }
    }
}

@Composable
fun DetailLoadedUi(
    modifier: Modifier,
    post: Post
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 0.dp,
                top = 4.dp,
                bottom = 16.dp
            ),
            text = post.headline ?: "",
            style = MaterialTheme.typography.headlineSmall
        )

        AsyncImage(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(160.dp),
            model = post.image,
            contentDescription = "Content Description",
            fallback =  painterResource(id = R.drawable.ic_launcher_background),
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            error = painterResource(id = R.drawable.ic_launcher_background)
        )

        HorizontalDivider()
    }
}
