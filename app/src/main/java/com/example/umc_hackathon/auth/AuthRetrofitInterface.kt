package com.example.umc_hackathon.auth

import retrofit2.Call
import retrofit2.http.*

interface AuthRetrofitInterface {
    @POST("/users")
    fun join(@Body user:User): Call<JoinResponse>

    @POST("/auth/login")
    fun login(@Body user:User): Call<LoginResponse>

    @GET("/users")
    fun userInfo(
        @Query("userId") userId: Long
    ): Call<UserInfoResponse>
}