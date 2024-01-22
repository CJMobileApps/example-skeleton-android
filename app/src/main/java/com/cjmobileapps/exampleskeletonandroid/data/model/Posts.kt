package com.cjmobileapps.exampleskeletonandroid.data.model

import com.google.gson.annotations.SerializedName

data class Posts(
    @SerializedName("Posts") val posts: List<Post>
)

data class Post(
    val id: String?,
    val headline: String?,
    val image: String?,
    var upvotes: Int?,
    val author: String?
)
