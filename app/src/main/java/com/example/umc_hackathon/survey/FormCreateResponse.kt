package com.example.umc_hackathon.survey

import com.google.gson.annotations.SerializedName

data class FormCreateResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: FormCreateResult
)

data class FormCreateResult (
    @SerializedName("postId") val postId: Long
)
