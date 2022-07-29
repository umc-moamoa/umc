package com.example.umc_hackathon.post

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostRetrofitInterface {
    @GET("/posts")
    fun getAllPosts(
        @Query("categoryId") category: Int
    ): Call<PostListResponse>

    @GET("/posts/content/{postId}")
    fun getPostDetail(
        @Path("postId") postId: Int
    ): Call<Post>

}