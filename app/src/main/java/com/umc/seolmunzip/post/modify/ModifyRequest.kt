package com.umc.seolmunzip.post.modify

import com.google.gson.annotations.SerializedName

data class ModifyRequest(
    @SerializedName("postId") val postId: Long,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("deadline") val deadline: String,
    @SerializedName("postUserId") val postUserId: Long
)
