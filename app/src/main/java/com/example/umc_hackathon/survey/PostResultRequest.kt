package com.example.umc_hackathon.survey

import com.example.umc_hackathon.post.PostDetailResult
import com.google.gson.annotations.SerializedName
import java.util.*

data class PostResultRequest(
    @SerializedName("categoryId") val categoryId: Long,
    @SerializedName("shortCount") val shortCount: Int,
    @SerializedName("longCount") val longCount: Int,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("deadline") val deadline: Date,
    @SerializedName("postDetails") val postDetails: List<String>
)


