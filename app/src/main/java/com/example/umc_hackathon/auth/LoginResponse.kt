package com.example.umc_hackathon.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code")val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: LoginResult?
)

data class LoginResult (
    @SerializedName("jwt") val jwt: String,
    @SerializedName("userId") val userId: Long
)
