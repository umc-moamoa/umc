package com.example.umc_hackathon.survey

import com.example.umc_hackathon.post.LikeResponse
import com.example.umc_hackathon.post.PostDetailResponse
import retrofit2.Call
import retrofit2.http.*

interface FormRetrofitInterface {
    // 설문조사 폼 등록하기
    @POST("/posts")
    fun formCreate (
        @Body formCreateRequest: FormCreateRequest,
        @Header("x-access-token") jwt: String
    ): Call<FormCreateResponse>

    // 설문조사 문항 확인하기
    @GET("/posts/{postId}")
    fun getFormDetail(
        @Path("postId") postId: Long,
        @Header("x-access-token") jwt: String
    ): Call<FormDetailResponse>

    // 설문조사 답변 등록하기
    @POST("/results")
    fun formInput (
        @Body formInputRequest: FormInputRequest,
        @Header("x-access-token") jwt: String
    ): Call<FormInputResponse>
}