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
    private lateinit var joinCheckView: JoinCheckView
    private lateinit var userSettingView: UserSettingView

    fun setJoinView(joinView: JoinView) {
        this.joinView = joinView
    }

    fun setLoginView(loginView: LoginView) {
        this.loginView = loginView
    }

    fun setUserInfoView(userInfoView: UserInfoView) {
        this.userInfoView = userInfoView
    }

    fun setJoinCheckView(joinCheckView: JoinCheckView) {
        this.joinCheckView = joinCheckView
    }

    fun setEmailView(emailView: JoinCheckView) {
        this.joinCheckView = joinCheckView
    }

    fun setUserSettingView(userSettingView: UserSettingView){
        this.userSettingView = userSettingView
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

    fun userInfo(jwt: String) {
    val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.userInfo(jwt).enqueue(object : Callback<UserInfoResponse> {
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

    fun joinCheck(id: String, nick: String) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.joinIdCheck(id).enqueue(object: Callback<JoinCheckResponse> {
            override fun onResponse(call: Call<JoinCheckResponse>, response: Response<JoinCheckResponse>) {
                if(response.body() != null) {
                    Log.d("JOINIDCHECK/SUCCESS", response.toString())

                    val resp: JoinCheckResponse = response.body()!!
                    when(resp.code) {
                        1000 -> joinCheckView.onJoinIdCheckSuccess()
                        else -> joinCheckView.onJoinIdCheckFailure()
                    }
                }
            }

            override fun onFailure(call: Call<JoinCheckResponse>, t: Throwable) {
                Log.d("JOINIDCHECK/FAILURE", t.message.toString())
            }
        })

        authService.joinNickCheck(nick).enqueue(object: Callback<JoinCheckResponse> {
            override fun onResponse(call: Call<JoinCheckResponse>, response: Response<JoinCheckResponse>) {
                if(response.body() != null) {
                    Log.d("JOINNICKCHECK/SUCCESS", response.toString())

                    val resp: JoinCheckResponse = response.body()!!
                    when(resp.code) {
                        1000 -> joinCheckView.onJoinNickCheckSuccess()
                        else -> joinCheckView.onJoinNickCheckFailure()
                    }
                }
            }

            override fun onFailure(call: Call<JoinCheckResponse>, t: Throwable) {
                Log.d("JOINNICKCHECK/FAILURE", t.message.toString())
            }
        })

        Log.d("JOINCHECK()/", "메소드")
    }

    fun emailSend(email : String) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.emailSend(email).enqueue(object : Callback<EmailResponse>{
            override fun onResponse(call: Call<EmailResponse>, response: Response<EmailResponse>) {
                if(response.body() != null) {
                    Log.d("emailSend-success", response.toString())

                    val response: EmailResponse = response.body()!!
                    when(val code = response.code) {
                        1000 ->
                    }
                }
            }

            override fun onFailure(call: Call<EmailResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun deleteUser(jwt: String) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.deleteUser(jwt).enqueue(object : Callback<UserDeleteResponse> {
            override fun onResponse(call: Call<UserDeleteResponse>, response: Response<UserDeleteResponse>) {
                if(response.body() != null) {
                    Log.d("USERINFO/성공", response.toString())

                    val resp: UserDeleteResponse = response.body()!!
                    when(val code = resp.code) {
                        1000 -> userSettingView.onUserDeleteSuccess()
                        else -> userSettingView.onUserDeleteFailure()
                    }
                }
            }

            override fun onFailure(call: Call<UserDeleteResponse>, t: Throwable) {
                Log.d("USERINFO/실패", t.message.toString())
            }

        })

        Log.d("service/userInfo()", "메소드")
    }
}