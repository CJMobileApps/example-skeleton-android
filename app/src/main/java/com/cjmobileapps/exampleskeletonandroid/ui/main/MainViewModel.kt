package com.cjmobileapps.exampleskeletonandroid.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cjmobileapps.exampleskeletonandroid.data.ExampleSkeletonAndroidRepository
import com.cjmobileapps.exampleskeletonandroid.data.model.Post
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

    private val mainState = MutableLiveData<MainState>(MainState.MainLoadedState())

    val stateLiveData: LiveData<MainState> = mainState

    private val tag = MainViewModel::class.java.simpleName

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

    sealed class MainState {

        data class MainLoadedState(
            val posts: List<Post> = emptyList()
        ) : MainState()
    }

}
