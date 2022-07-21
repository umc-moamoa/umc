package com.example.umc_hackathon.interest

import com.google.gson.annotations.SerializedName

data class Interest(
    @SerializedName(value = "interest_id") var interestId: Long,
    @SerializedName(value = "post_id") var postId: Long,
    @SerializedName(value = "user_id") var userId: Long
)
