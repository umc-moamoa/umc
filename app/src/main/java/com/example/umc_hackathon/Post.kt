package com.example.umc_hackathon

import com.google.gson.annotations.SerializedName
import java.util.*

data class Post(
    @SerializedName(value = "postId") var postId: Long,
    @SerializedName(value = "title") var title: String,
    @SerializedName(value = "userId") var userId: Long,
    @SerializedName(value = "point") var point: Int,
    @SerializedName(value = "categoryId") var category: Int,
    //카테고리를 string처리 나중에
    @SerializedName(value = "content") var content: String,
    @SerializedName(value = "status") var status: Boolean,
    @SerializedName(value = "deadline") var deadline: Date
    )
