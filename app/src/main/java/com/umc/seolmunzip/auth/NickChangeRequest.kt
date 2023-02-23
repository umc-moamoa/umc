package com.umc.seolmunzip.auth

import com.google.gson.annotations.SerializedName

data class NickChangeRequest(
    @SerializedName("newNickName") val newNickName: String
)
