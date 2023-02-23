package com.umc.seolmunzip.my.point

import com.google.gson.annotations.SerializedName

data class MyPoint(
    @SerializedName("state") var state: String,
    @SerializedName("date") var date: String,
    @SerializedName("point") var point: Int
)