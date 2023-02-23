package com.umc.seolmunzip.post.detail

import com.google.gson.annotations.SerializedName

data class PostDetailRequest(
    @SerializedName("postId") val postId: Long
)
