package com.example.umc_hackathon.auth

import com.google.gson.annotations.SerializedName

data class UserInfoRequest(
    @SerializedName("userId") val userId: Long
)
