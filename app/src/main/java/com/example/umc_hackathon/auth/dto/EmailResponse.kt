package com.example.umc_hackathon.auth.dto

import com.google.gson.annotations.SerializedName

data class EmailResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String
)