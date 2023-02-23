package com.umc.seolmunzip.auth.dto

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email") var id: String,
    @SerializedName("pwd") var pwd: String
)

data class UserSign(
    @SerializedName("email") var id: String,
    @SerializedName("nick") var nick: String,
    @SerializedName("pwd") var pwd: String
)
