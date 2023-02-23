package com.umc.seolmunzip.auth.dto

import com.google.gson.annotations.SerializedName

data class UserInfoRequest(
    @SerializedName("x-access-token") val accessToken: String,
    @SerializedName("REFRESH-TOKEN") val refreshToken: String
)
