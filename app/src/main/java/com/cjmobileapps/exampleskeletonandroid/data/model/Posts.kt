package com.cjmobileapps.exampleskeletonandroid.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Posts(
    @SerializedName("Posts") val posts: List<Post>
) : Parcelable

@Parcelize
data class Post(
    val id: String?,
    val headline: String?,
    val image: String?,
    var upvotes: Int?,
    val author: String?
) : Parcelable
