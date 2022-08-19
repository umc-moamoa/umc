package com.example.umc_hackathon.post

import com.example.umc_hackathon.post.result.ResultResponse
import retrofit2.Call
import retrofit2.http.*

interface PostRetrofitInterface {
    @GET("/posts")
    fun getPostList(
        @Query("categoryId") category: Long
    ): Call<PostListResponse>

    @GET("/users/interest")
    fun getInterestSurveyList(
        @Header("x-access-token") jwt: String
    ): Call<PostListResponse>

    @GET("/users/partPost")
    fun getParticipatedSurvey(
        @Header("x-access-token") jwt: String
    ): Call<PostListResponse>

    @GET("/users/userPost")
    fun getMySurvey(
        @Header("x-access-token") jwt: String
    ): Call<MySurveyResponse>

    @GET("/posts/content/{postId}")
    fun getPostDetail(
        @Path("postId") postId: Long,
        @Header("x-access-token") jwt: String
    ): Call<PostDetailResponse>

    @POST("/posts/interest/{postId}")
    fun likePost(
        @Path("postId") postId: Long,
        @Header("x-access-token") jwt: String
    ): Call<LikeResponse>

    @DELETE("/posts/interest/{postId}")
    fun dislikePost(
        @Path("postId") postId: Long,
        @Header("x-access-token") jwt: String
    ): Call<StringResultResponse>

    @GET("/results/{postDetailId}")
    fun getResults(
        @Path("postDetailId") postDetailId: Long
    ): Call<ResultResponse>

    @PATCH("/posts/{postId}/status")
    fun deletePost(
        @Path("postId") postId: Long,
        @Header("x-access-token") jwt: String
    ): Call<StringResultResponse> //같은 형식이라 공유해서 사용
}