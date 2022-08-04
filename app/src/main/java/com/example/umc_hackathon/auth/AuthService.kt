package com.example.umc_hackathon.auth

import android.util.Log
import com.example.umc_hackathon.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {

    private lateinit var joinView: JoinView

    fun setJoinView(joinView: JoinView) {
        this.joinView = joinView
    }

    fun join(user: User) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.join(user).enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("JOIN/SUCCESS", response.toString())
                val resp: AuthResponse = response.body()!!

                when(resp.code) {
                    1000 -> joinView.onJoinSuccess()
                    else -> joinView.onJoinFailure()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("JOIN/FAILURE", t.message.toString())
            }
        })

        Log.d("JOIN()/", "메소드")
    }
}