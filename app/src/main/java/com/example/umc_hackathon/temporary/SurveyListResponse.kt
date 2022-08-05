package com.example.umc_hackathon.temporary

import com.google.gson.annotations.SerializedName

data class SurveyListResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: SurveyListResult
)

data class SurveyListResult (
    @SerializedName("surveys") val surveys: List<SurveyList>
)

data class SurveyList(
    @SerializedName("postId") val postId: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("point") val point: Int,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("deadline") val deadline: Int
)
