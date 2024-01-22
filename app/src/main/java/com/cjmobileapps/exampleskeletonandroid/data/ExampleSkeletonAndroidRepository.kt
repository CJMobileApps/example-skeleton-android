package com.cjmobileapps.exampleskeletonandroid.data

import com.cjmobileapps.exampleskeletonandroid.data.model.Post
import com.cjmobileapps.exampleskeletonandroid.data.model.Posts
import com.cjmobileapps.exampleskeletonandroid.network.MainApi
import com.cjmobileapps.exampleskeletonandroid.util.coroutine.CoroutineDispatchers
import com.cjmobileapps.exampleskeletonandroid.util.onSuccess
import kotlinx.coroutines.withContext
import retrofit2.Response

class ExampleSkeletonAndroidRepository(
    private val mainApi: MainApi,
    private val coroutineDispatchers: CoroutineDispatchers
) {

    var posts: List<Post> = emptyList()

    suspend fun getPosts(): Response<Posts> {
       return withContext(coroutineDispatchers.io) {
           mainApi
               .getPostsAsync()
               .await()
               .onSuccess { posts ->
                   this@ExampleSkeletonAndroidRepository.posts = posts.posts
               }
       }
    }
}
