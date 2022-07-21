package com.example.umc_hackathon.interest

import com.google.gson.annotations.SerializedName

data class InterestResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Int
)
