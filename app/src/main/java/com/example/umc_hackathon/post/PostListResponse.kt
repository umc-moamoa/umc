package com.example.umc_hackathon.post

import com.google.gson.annotations.SerializedName

data class PostListResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<PostList>
)

data class PostList (
    @SerializedName("postId") val postId: Long,
    @SerializedName("point") val point: Int,
    @SerializedName("title") val title: String,
    @SerializedName("status") val status: String,
    @SerializedName("qcount") val qcount: Int,
    @SerializedName("dday") val dday: Int
)