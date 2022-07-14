package com.example.umc_hackathon

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostRetrofitInterface {
    @GET("/post")
    fun getAllPosts(): Call<List<Post>>

}