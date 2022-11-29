package com.example.umc_hackathon.post.detail

import com.google.gson.annotations.SerializedName

data class PostDetailRequest(
    @SerializedName("postId") val postId: Long
)
