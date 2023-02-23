package com.umc.seolmunzip.post.list

import com.google.gson.annotations.SerializedName

data class PostListRequest(
    @SerializedName("categoryId") val categoryId: Int
)
