package com.example.umc_hackathon.post

import com.example.umc_hackathon.post.result.DetailIdResponse
import com.example.umc_hackathon.post.result.ResultResponse
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
        @Header("x-access-token") accessToken: String,
        @Header("REFRESH-TOKEN") refreshToken: String
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

    @GET("/results/repeat/{postId}")
    fun getDetailId(
        @Path("postId") postId: Long
    ): Call<DetailIdResponse>

    @PATCH("/posts/{postId}/status")
    fun deletePost(
        @Path("postId") postId: Long,
        @Header("x-access-token") jwt: String
    ): Call<StringResultResponse> //같은 형식이라 공유해서 사용

    // 포인트 내역 조회
    @GET("/users/point/recent")
    fun getRecentMyPoint(
        @Header("x-access-token") jwt: String
    ): Call<MyPointResponse>

    @GET("/users/point/former")
    fun getFormerMyPoint(
        @Header("x-access-token") jwt: String
    ): Call<MyPointResponse>
}