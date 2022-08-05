package com.example.umc_hackathon.auth

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: UserInfoResult
)

data class UserInfoResult(
    @SerializedName("nickName") val nickName: String,
    @SerializedName("point") val point: Int,
    @SerializedName("postCount") val postCount: Int
)
