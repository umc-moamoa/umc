package com.example.umc_hackathon.interest

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface InterestRetrofitInterface {

    @POST("/posts/{postId}/{userId}")
    fun makeInterest(
        @Path("postId") postId: Int,
        @Path("userId") userId: Int
    ): Call<InterestResponse>
}