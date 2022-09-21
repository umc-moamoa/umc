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
        @Header("x-access-token") jwt: String
    ): Call<UserInfoResponse>

    @DELETE("/users")
    fun deleteUser(
        @Header("x-access-token") jwt: String
    ): Call<UserDeleteResponse>

    @GET("/users/id/{id}")
    fun joinIdCheck(@Path("id") id: String): Call<JoinCheckResponse>

    @GET("/users/nick/{nick}")
    fun joinNickCheck(@Path("nick") nick: String): Call<JoinCheckResponse>
}