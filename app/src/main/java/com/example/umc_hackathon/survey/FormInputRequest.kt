package com.example.umc_hackathon.survey

import com.google.gson.annotations.SerializedName

data class FormInputRequest(
    @SerializedName("postId") val postId: Long,
    @SerializedName("postDetailResults") val postDetailResults: List<List<List<String>>>
)
