package com.example.umc_hackathon.survey.participate

import com.google.gson.annotations.SerializedName

data class FormInputRequest(
    @SerializedName("postId") var postId: Long,
    @SerializedName("postDetailResults") var postDetailResults: List<Answer>
)

data class Answer(
    var detailId: Long,
    var answer: List<String>
)
