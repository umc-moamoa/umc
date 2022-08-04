package com.example.umc_hackathon.auth

import com.google.gson.annotations.SerializedName

data class JoinResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code")val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: JoinResult?
)

data class JoinResult (
    @SerializedName("jwt") val jwt: String,
    @SerializedName("userId") val userId: Int
)
