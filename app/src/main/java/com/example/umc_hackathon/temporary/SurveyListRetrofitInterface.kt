package com.example.umc_hackathon.temporary

import retrofit2.Call
import retrofit2.http.GET

interface SurveyListRetrofitInterface {
    @GET("/posts")
    fun getSurveyList(): Call<SurveyListResponse>
}