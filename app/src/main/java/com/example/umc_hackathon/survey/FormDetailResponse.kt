package com.example.umc_hackathon.survey

import com.google.gson.annotations.SerializedName

// 설문조사 문항 확인
data class FormDetailResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<FormDetail>,
)

data class FormDetail (
    @SerializedName("postDetailId") val postDetailId: Long,
    @SerializedName("question") val question: String,
    @SerializedName("format") val format: Int,
    @SerializedName("items") val items: List<String>
)
