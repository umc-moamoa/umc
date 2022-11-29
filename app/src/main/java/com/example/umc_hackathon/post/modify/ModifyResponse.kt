package com.example.umc_hackathon.post.modify

import com.google.gson.annotations.SerializedName

data class ModifyResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Long
)
