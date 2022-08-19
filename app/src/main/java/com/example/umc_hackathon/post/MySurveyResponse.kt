package com.example.umc_hackathon.post

import com.google.gson.annotations.SerializedName

data class MySurveyResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<MySurveyList>
)

data class MySurveyList (
    @SerializedName("postId") val postId: Int,
    @SerializedName("postTitle") val postTitle: String,
    @SerializedName("point") val point: Int,
    @SerializedName("postResultCount") val postResultCount: Int,
    @SerializedName("status") val status: String,
    @SerializedName("qcount") val qcount: Int,
    @SerializedName("dday") val dday: Int,
)
