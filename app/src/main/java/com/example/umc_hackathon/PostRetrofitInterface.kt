package com.example.umc_hackathon

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PostRetrofitInterface {
    @GET("/post")
    fun getAllPosts(
        @Query("category") category: Int
    ): Call<List<Post>>

}