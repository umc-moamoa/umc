package com.example.umc_hackathon.post

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostRetrofitInterface {
    @GET("/posts")
    fun getPostList(@Query("categoryId") category: Long): Call<PostListResponse>
}