package com.example.umc_hackathon.auth

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") var id: String,
    @SerializedName("nick") var nick: String,
    @SerializedName("pwd") var pwd: String
)
