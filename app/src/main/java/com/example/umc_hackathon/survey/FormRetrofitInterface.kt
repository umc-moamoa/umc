package com.example.umc_hackathon.survey

import com.example.umc_hackathon.my.survey.MyAnswerResponse
import com.example.umc_hackathon.post.modify.ModifyRequest
import com.example.umc_hackathon.post.modify.ModifyResponse
import com.example.umc_hackathon.survey.create.FormCreateRequest
import com.example.umc_hackathon.survey.create.FormCreateResponse
import com.example.umc_hackathon.survey.participate.FormInputRequest
import com.example.umc_hackathon.survey.participate.FormInputResponse
import retrofit2.Call
import retrofit2.http.*

interface FormRetrofitInterface {
    // 설문조사 폼 등록하기
    @POST("/posts")
    fun formCreate (
        @Body formCreateRequest: FormCreateRequest,
        @Header("x-access-token") accessToken: String
    ): Call<FormCreateResponse>

    // 설문조사 문항 확인하기
    @GET("/posts/{postId}")
    fun getFormDetail(
        @Path("postId") postId: Long,
        @Header("x-access-token") accessToken: String,
        @Header("REFRESH-TOKEN") refreshToken: String
    ): Call<FormDetailResponse>


    @POST("/results")
    fun submitAnswer(
        @Body formInputRequest: FormInputRequest,
        @Header("x-access-token") accessToken: String,
        @Header("REFRESH-TOKEN") refreshToken: String
    ): Call<FormInputResponse>

    // 설문조사 수정하기
    @PATCH("/posts")
    fun formModify (
        @Body modifyRequest: ModifyRequest,
        @Header("x-access-token") jwt: String
    ): Call<ModifyResponse>

    @GET("/users/answer/{postId}")
    fun getMyAnswer(
        @Path("postId") postId: Long,
        @Header("x-access-token") accessToken: String,
        @Header("REFRESH-TOKEN") refreshToken: String
    ): Call<MyAnswerResponse>
}