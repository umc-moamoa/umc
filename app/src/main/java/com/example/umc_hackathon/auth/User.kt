package com.example.umc_hackathon.auth

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email") var id: String,
    @SerializedName("pwd") var pwd: String
)
