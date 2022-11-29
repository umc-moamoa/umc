package com.example.umc_hackathon.auth

import com.google.gson.annotations.SerializedName

data class NickChangeRequest(
    @SerializedName("newNickName") val newNickName: String
)
