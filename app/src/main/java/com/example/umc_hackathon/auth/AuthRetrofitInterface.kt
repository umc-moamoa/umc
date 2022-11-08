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
        @Header("x-access-token") accessToken: String,
        @Header("REFRESH-TOKEN") refreshToken: String
    ): Call<UserInfoResponse>

    @DELETE("/users")
    fun deleteUser(
        @Header("x-access-token") accessToken: String,
        @Header("REFRESH-TOKEN") refreshToken: String
    ): Call<UserDeleteResponse>

    @GET("/users/id/{id}")
    fun joinIdCheck(@Path("id") id: String): Call<JoinCheckResponse>

    @GET("/email/send")
    fun emailSend(@Body email:String): Call<EmailResponse>

    @POST("/email/auth")
    fun emailCertificate(@Body certifiedCode:String): Call<EmailResponse>

    @GET("/users/nick/{nick}")
    fun joinNickCheck(
        @Path("nick") nick: String
    ): Call<JoinCheckResponse>

    @GET("/auth/refresh")
    fun getReAccessToken(
        @Header("x-access-token") accessToken: String,
        @Header("REFRESH-TOKEN") refreshToken: String
    ): Call<ReAccessTokenResponse>
}