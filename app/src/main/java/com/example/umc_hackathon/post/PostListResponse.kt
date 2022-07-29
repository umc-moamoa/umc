package com.example.umc_hackathon.post

import com.google.gson.annotations.SerializedName

data class PostListResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<Post>
)
