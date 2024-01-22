package com.cjmobileapps.exampleskeletonandroid.ui.main.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cjmobileapps.exampleskeletonandroid.data.ExampleSkeletonAndroidRepository
import com.cjmobileapps.exampleskeletonandroid.data.model.Post
import com.cjmobileapps.exampleskeletonandroid.ui.NavItem
import com.cjmobileapps.exampleskeletonandroid.util.coroutine.CoroutineDispatchers
import com.cjmobileapps.exampleskeletonandroid.util.onError
import com.cjmobileapps.exampleskeletonandroid.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exampleSkeletonAndroidRepository: ExampleSkeletonAndroidRepository,
    coroutineDispatchers: CoroutineDispatchers
) : ViewModel() {

    private val compositeJob = Job()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(tag, "coroutineExceptionHandler() error occurred: $throwable \n ${throwable.message}")
    }

    private val coroutineContext =
        compositeJob + coroutineDispatchers.main + exceptionHandler + SupervisorJob()

    private val mainState = mutableStateOf<MainState>(MainState.MainLoadedState())

    private val tag = MainViewModel::class.java.simpleName

    fun getState() = mainState.value

    init {
        viewModelScope.launch(coroutineContext) {
            exampleSkeletonAndroidRepository
                .getPosts()
                .onSuccess { posts ->
                    mainState.value = MainState.MainLoadedState(posts = posts.posts)
                }
                .onError { _, _ -> }
        }
    }

    fun getDuckItListNavRouteUiState(): MainNavRouteUi {
        val state = getState()
        if (state !is MainState.MainLoadedState) return MainNavRouteUi.Idle
        return state.mainNavRouteUi.value
    }

    fun resetNavRouteUiToIdle() {
        val state = getState()
        if (state !is MainState.MainLoadedState) return
        state.mainNavRouteUi.value = MainNavRouteUi.Idle
    }

    fun goToDetailUi(postId: String) {
        val state = getState()
        if (state !is MainState.MainLoadedState) return
        state.mainNavRouteUi.value = MainNavRouteUi.GoToLogInScreenUi(postId)
    }

    sealed class MainState {

        data class MainLoadedState(
            val posts: List<Post> = emptyList(),
            val mainNavRouteUi: MutableState<MainNavRouteUi> = mutableStateOf(
                MainNavRouteUi.Idle
            )
        ) : MainState()
    }

    sealed class MainNavRouteUi {

        data object Idle : MainNavRouteUi()

        data class GoToLogInScreenUi(
            val postId: String
        ) : MainNavRouteUi() {

            fun getNavRouteWithArguments(): String =
                NavItem.Detail.getNavRouteWithArguments(postId = postId)
        }
    }
}
