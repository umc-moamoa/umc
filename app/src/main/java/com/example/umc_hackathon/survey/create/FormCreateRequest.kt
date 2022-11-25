package com.example.umc_hackathon.survey.create

import com.google.gson.annotations.SerializedName
import java.util.*

data class FormCreateRequest(
    @SerializedName("categoryId") val categoryId: Long,
    @SerializedName("shortCount") val shortCount: Int,
    @SerializedName("longCount") val longCount: Int,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("deadline") val deadline: String,
    @SerializedName("postDetails") val postDetails: List<List<String>>
)
