package com.umc.seolmunzip.auth.dto

import com.google.gson.annotations.SerializedName

data class UserDeleteResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: UserInfoResult
)

data class UserDeleteResult(
    @SerializedName("nickName") val nickName: String,
    @SerializedName("id") val id: String,
    @SerializedName("pwd") val pwd: String,
    @SerializedName("point") val point: Int
)
