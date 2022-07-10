package com.example.umc_hackathon

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName(value = "title") var title: String
)
