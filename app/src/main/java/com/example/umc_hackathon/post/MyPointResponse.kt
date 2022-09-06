package com.example.umc_hackathon.post

import com.google.gson.annotations.SerializedName

data class MyPointResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: MyPointList
)

data class MyPointList (
    @SerializedName("point") val point: Int,
    @SerializedName("pointHistoryRecent") val pointHistoryRecent: List<PointRecentResult>?,
    @SerializedName("pointHistoryFormer") val pointHistoryFormer: List<PointFormerResult>?
)

data class PointRecentResult (
    @SerializedName("addAmount") val addAmount: Int,
    @SerializedName("subAmount") val subAmount: Int,
    @SerializedName("created") val created: String
)

data class PointFormerResult (
    @SerializedName("addAmount") val addAmount: Int,
    @SerializedName("subAmount") val subAmount: Int,
    @SerializedName("created") val created: String
)
