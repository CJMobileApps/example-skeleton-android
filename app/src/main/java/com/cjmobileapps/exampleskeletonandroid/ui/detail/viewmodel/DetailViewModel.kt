package com.cjmobileapps.exampleskeletonandroid.ui.detail.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cjmobileapps.exampleskeletonandroid.data.ExampleSkeletonAndroidRepository
import com.cjmobileapps.exampleskeletonandroid.data.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    exampleSkeletonAndroidRepository: ExampleSkeletonAndroidRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val postId: String = checkNotNull(savedStateHandle["postId"])

    private val detailState = mutableStateOf<DetailState>(DetailState.Idle)

    fun getState() = detailState.value

    init {
        detailState.value = DetailState.DetailLoadedState(
            exampleSkeletonAndroidRepository.posts.first {
                it.id == postId
            }
        )
    }

    sealed class DetailState {

        data object Idle : DetailState()

        data class DetailLoadedState(
            val post: Post
        ) : DetailState()
    }
}
