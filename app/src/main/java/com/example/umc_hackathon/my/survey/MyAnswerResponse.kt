package com.example.umc_hackathon.my.survey

import com.google.gson.annotations.SerializedName

data class MyAnswerResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ArrayList<MyAnswer>
)

data class MyAnswer (
    @SerializedName("format") val format: Long,
    @SerializedName("result") val result: ArrayList<String>
)
