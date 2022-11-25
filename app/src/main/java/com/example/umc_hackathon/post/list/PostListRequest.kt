package com.example.umc_hackathon.post.list

import com.google.gson.annotations.SerializedName

data class PostListRequest(
    @SerializedName("categoryId") val categoryId: Int
)
