package com.cjmobileapps.exampleskeletonandroid.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.cjmobileapps.exampleskeletonandroid.R
import com.cjmobileapps.exampleskeletonandroid.data.model.Post
import com.cjmobileapps.exampleskeletonandroid.ui.main.viewmodel.MainViewModel

@Composable
fun MainUi(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    Scaffold { innerPadding ->
        when (val state = mainViewModel.getState()) {
            is MainViewModel.MainState.MainLoadedState -> {
                MainLoadedUi(
                    modifier = Modifier
                        .padding(innerPadding),
                    posts = state.posts,
                    navController = navController,
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}

@Composable
fun MainLoadedUi(
    modifier: Modifier,
    posts: List<Post>,
    mainViewModel: MainViewModel,
    navController: NavController
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .testTag("DuckItListUi")
    ) {
        items(posts) { post ->
            Column(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    post.id?.let { id ->
                        mainViewModel.goToDetailUi(id)
                    }
                }
            ) {
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
                    fallback = painterResource(id = R.drawable.ic_launcher_background),
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    error = painterResource(id = R.drawable.ic_launcher_background)
                )

                HorizontalDivider()
            }
        }
    }

    when (val navigateRouteUiValue = mainViewModel.getDuckItListNavRouteUiState()) {
        is MainViewModel.MainNavRouteUi.Idle -> {}
        is MainViewModel.MainNavRouteUi.GoToLogInScreenUi -> {
            navController.navigate(navigateRouteUiValue.getNavRouteWithArguments())
            mainViewModel.resetNavRouteUiToIdle()
        }
    }
}
