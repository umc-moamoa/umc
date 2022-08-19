package com.example.umc_hackathon.post

import com.example.umc_hackathon.auth.UserInfoResponse
import retrofit2.Call
import retrofit2.http.*

interface PostRetrofitInterface {
    // 카테고리별 설문조사
    @GET("/posts")
    fun getPostList(
        @Query("categoryId") category: Long
    ): Call<PostListResponse>

    // 인기있는 설문조사
    @GET("/posts/desc")
    fun getPopularSurvey(): Call<PostListResponse>

    // 참여를 기다리는 설문조사
    @GET("/posts/asc")
    fun getWaitingSurvey(): Call<PostListResponse>

    // 관심있는 설문조사
    @GET("/users/interest")
    fun getInterestSurveyList(
        @Header("x-access-token") jwt: String
    ): Call<PostListResponse>

    // 참여한 설문조사
    @GET("/users/partPost")
    fun getParticipatedSurvey (
        @Header("x-access-token") jwt: String
    ): Call<PostListResponse>

    // 나의 설문조사
    @GET("/users/userPost")
    fun getMySurvey(
        @Header("x-access-token") jwt: String
    ): Call<MySurveyResponse>

    // 상세 페이지
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
    ): Call<LikeResponse>

    // 포인트 내역 조회
    @GET("/users/point/recent")
    fun getMyPoint(
        @Header("x-access-token") jwt: String
    ): Call<MyPointResponse>
}