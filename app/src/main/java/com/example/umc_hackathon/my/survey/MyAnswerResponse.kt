package com.example.umc_hackathon.my.survey

import com.google.gson.annotations.SerializedName

data class MyAnswerResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: MyAnswerList
)

data class MyAnswerList (
   @SerializedName("getUserResultRes") val getUserResultRes: List<MyAnswer>
)

data class MyAnswer (
    @SerializedName("result") val result: String
)
