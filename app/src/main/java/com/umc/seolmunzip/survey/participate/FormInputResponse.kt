package com.umc.seolmunzip.survey.participate

import com.google.gson.annotations.SerializedName

data class FormInputResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: FormInputResult
)

data class FormInputResult (
    @SerializedName("resultId") val resultId: Long,
    @SerializedName("point") val point: Int
)