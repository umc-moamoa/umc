package com.example.umc_hackathon.post

import com.google.gson.annotations.SerializedName
import java.util.*

data class Post(
    @SerializedName(value = "postId") var postId: Int,
    @SerializedName(value = "userId") var userId: Int,
    @SerializedName(value = "point") var point: Int,
    @SerializedName(value = "title") var title: String,
    /*
    @SerializedName(value = "categoryId") var category: Int,
    카테고리 req param
    //카테고리를 string 처리 나중에*/

    @SerializedName(value = "content") var content: String,
    @SerializedName(value = "deadline") var deadline: String
    )