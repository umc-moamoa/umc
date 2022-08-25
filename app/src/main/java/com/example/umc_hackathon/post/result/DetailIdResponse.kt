package com.example.umc_hackathon.post.result

import com.google.gson.annotations.SerializedName

data class DetailIdResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: IdResult
)
data class IdResult(
    @SerializedName("startPostDetailId") val startId: Long,
    @SerializedName("endPostDetailId") val endId: Long
)

