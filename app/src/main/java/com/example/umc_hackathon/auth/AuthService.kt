package com.example.umc_hackathon.auth

import android.util.Log
import com.example.umc_hackathon.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {

    private lateinit var joinView: JoinView
    private lateinit var loginView: LoginView

    fun setJoinView(joinView: JoinView) {
        this.joinView = joinView
    }

    fun setLoginView(loginView: LoginView) {
        this.loginView = loginView
    }

    fun join(user: User) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.join(user).enqueue(object: Callback<JoinResponse> {
            override fun onResponse(call: Call<JoinResponse>, response: Response<JoinResponse>) {
                Log.d("JOIN/SUCCESS", response.toString())
                val resp: JoinResponse = response.body()!!

                when(resp.code) {
                    1000 -> joinView.onJoinSuccess()
                    else -> joinView.onJoinFailure()
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
                Log.d("LOGIN/SUCCESS", response.toString())
                Log.d("RESP/", " " + response.body()!!)
                val resp: LoginResponse = response.body()!!

                when (val code = resp.code) {
                    1000 -> loginView.onLoginSuccess(code, resp.result!!)
                    else -> loginView.onLoginFailure(code)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("LOGIN/FAILURE", t.message.toString())
            }
        })

        Log.d("LOGIN()/", "메소드")
    }
}