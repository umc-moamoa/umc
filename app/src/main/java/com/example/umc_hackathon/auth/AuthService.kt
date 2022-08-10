package com.example.umc_hackathon.auth

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.umc_hackathon.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {

    private lateinit var joinView: JoinView
    private lateinit var loginView: LoginView
    private lateinit var userInfoView: UserInfoView

    fun setJoinView(joinView: JoinView) {
        this.joinView = joinView
    }

    fun setLoginView(loginView: LoginView) {
        this.loginView = loginView
    }

    fun setUserInfoView(userInfoView: UserInfoView) {
        this.userInfoView = userInfoView
    }

    fun join(user: User) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.join(user).enqueue(object: Callback<JoinResponse> {
            override fun onResponse(call: Call<JoinResponse>, response: Response<JoinResponse>) {
                if(response.body() != null) {
                    Log.d("JOIN/SUCCESS", response.toString())

                    val resp: JoinResponse = response.body()!!
                    when(resp.code) {
                        1000 -> joinView.onJoinSuccess()
                        else -> joinView.onJoinFailure()
                    }
                }
            }

            override fun onFailure(call: Call<JoinResponse>, t: Throwable) {
                Log.d("JOIN/FAILURE", t.message.toString())
            }
        })

        Log.d("JOIN()/", "메소드")
    }

    fun login(user: User) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.login(user).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.body() != null) {
                    Log.d("LOGIN/SUCCESS", response.toString())

                    val resp: LoginResponse = response.body()!!
                    when (val code = resp.code) {
                        1000 -> loginView.onLoginSuccess(code, resp.result!!)
                        else -> loginView.onLoginFailure(code)
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("LOGIN/FAILURE", t.message.toString())
            }
        })

        Log.d("LOGIN()/", "메소드")
    }

    fun userInfo(userInfoRequest: UserInfoRequest) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.userInfo(userInfoRequest.userId).enqueue(object : Callback<UserInfoResponse> {
            override fun onResponse(call: Call<UserInfoResponse>, response: Response<UserInfoResponse>) {
                if(response.body() != null) {
                    Log.d("USERINFO/성공", response.toString())

                    val resp: UserInfoResponse = response.body()!!
                    when(val code = resp.code) {
                        1000 -> userInfoView.onUserInfoSuccess(code, resp.result!!)
                        else -> userInfoView.onUserInfoFailure(code)
                    }
                }
            }

            override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                Log.d("USERINFO/실패", t.message.toString())
            }
        })

        Log.d("service/userInfo()", "메소드")
    }
}