package com.example.umc_hackathon.post

import com.google.gson.annotations.SerializedName

data class PostListResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: PostListResult
)

data class PostListResult(
    @SerializedName("posts") val posts: List<PostList>
)

data class PostList (
    @SerializedName("postId") val postId: Int,
    @SerializedName("point") val point: Int,
    @SerializedName("title") val title: String,
    @SerializedName("status") val status: String,
    @SerializedName("qCount") val qCount: Int
)