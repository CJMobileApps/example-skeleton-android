package com.cjmobileapps.exampleskeletonandroid.network

import com.cjmobileapps.exampleskeletonandroid.data.model.Posts
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ExampleSkeletonAndroidApi {

    @GET("posts")
    fun getPostsAsync(): Deferred<Response<Posts>>

/***
    @POST("posts/{postId}/upvote")
    fun upvoteAsync(
    @Path("postId") postId: String
    ): Deferred<Response<Upvotes>>

    @POST("posts/{postId}/downvote")
    fun downvoteAsync(
    @Path("postId") postId: String
    ): Deferred<Response<Upvotes>>

    @POST("signin")
    fun signInAsync(
    @Body emailPasswordRequest: EmailPasswordRequest
    ): Deferred<Response<TokenResponse>>

    @POST("signup")
    fun signUpAsync(
    @Body emailPasswordRequest: EmailPasswordRequest
    ): Deferred<Response<TokenResponse>>

    @POST("posts")
    fun newPostAsync(
    @Header(NetworkConstants.AUTHORIZATION_HEADER) authorizationToken: String,
    @Body newPost: NewPostRequest
    ): Deferred<Response<Unit>>
***/
}
