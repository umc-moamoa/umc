package com.example.umc_hackathon.post

import com.google.gson.annotations.SerializedName

data class UrlResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Url
)

data class Url(
    @SerializedName("url") val url: String
)
