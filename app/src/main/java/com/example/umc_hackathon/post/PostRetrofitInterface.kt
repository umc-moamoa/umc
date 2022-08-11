package com.example.umc_hackathon.post

import com.example.umc_hackathon.auth.UserInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PostRetrofitInterface {
    @GET("/posts")
    fun getPostList(@Query("categoryId") category: Long): Call<PostListResponse>

    @GET("/users/interest")
    fun getInterestSurveyList(): Call<PostListResponse>

    @GET("/posts/content/{postId}")
    fun getPostDetail(
        @Path("postId") postId: Long,
        @Header("x-access-token") jwt: String
    ): Call<PostDetailResponse>
}