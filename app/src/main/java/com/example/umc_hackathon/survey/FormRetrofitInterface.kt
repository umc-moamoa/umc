package com.example.umc_hackathon.survey

import com.example.umc_hackathon.post.LikeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface FormRetrofitInterface {
    // 설문 등록하기
    @POST("/posts")
    fun formCreate (
        @Body formCreateRequest: FormCreateRequest,
        @Header("x-access-token") jwt: String
    ): Call<FormCreateResponse>
}