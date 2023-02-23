package com.umc.seolmunzip.post.modify

import com.google.gson.annotations.SerializedName

data class ModifyResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Long
)
