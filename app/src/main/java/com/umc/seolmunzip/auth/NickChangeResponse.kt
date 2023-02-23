package com.umc.seolmunzip.auth

import com.google.gson.annotations.SerializedName

data class NickChangeResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code")val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: NickChangeResult?
)

data class NickChangeResult (
    @SerializedName("nick") val nick: String,
    @SerializedName("id") val id: String
)
