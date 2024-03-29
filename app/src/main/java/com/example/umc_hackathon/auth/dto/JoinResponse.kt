package com.example.umc_hackathon.auth.dto

import com.google.gson.annotations.SerializedName

data class JoinResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code")val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: JoinResult?
)

data class JoinResult (
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String,
    @SerializedName("userId") val userId: Long
)
