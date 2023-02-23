package com.umc.seolmunzip.survey.participate

import com.google.gson.annotations.SerializedName

data class FormInputRequest(
    @SerializedName("postId") var postId: Long,
    @SerializedName("postDetailResults") var postDetailResults: ArrayList<ArrayList<ArrayList<String>>>
)

