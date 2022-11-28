package com.example.umc_hackathon.auth

import android.util.Log
import com.example.umc_hackathon.auth.dto.*
import com.example.umc_hackathon.auth.view.*
import com.example.umc_hackathon.getRetrofit
import com.example.umc_hackathon.post.StringResultResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {

    private final var TAG = "AuthService"

    private lateinit var joinView: JoinView
    private lateinit var loginView: LoginView
    private lateinit var userInfoView: UserInfoView
    private lateinit var joinCheckView: JoinCheckView
    private lateinit var nickCheckView: NickCheckView
    private lateinit var userSettingView: UserSettingView
    private lateinit var reAccessTokenView: ReAccessTokenView
    private lateinit var nickChangeView: NickChangeView

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
    
    fun setNickCheckView(nickCheckView: NickCheckView) {
        this.nickCheckView = nickCheckView
    }

    fun setEmailView(emailView: JoinCheckView) {
        this.joinCheckView = joinCheckView
    }

    fun setUserSettingView(userSettingView: UserSettingView){
        this.userSettingView = userSettingView
    }

    fun setReAccessTokenView(reAccessTokenView: ReAccessTokenView) {
        this.reAccessTokenView = reAccessTokenView
    }

    fun setNickChangeView(nickChangeView: NickChangeView) {
        this.nickChangeView = nickChangeView
    }

    fun join(user: UserSign) {
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

        authService.login(user).enqueue(object : Callback<StringResultResponse> {
            override fun onResponse(call: Call<StringResultResponse>, response: Response<StringResultResponse>) {
                if(response.body() != null) {
                    Log.d(TAG, "login() / onResponse() $response")

                    val resp: StringResultResponse = response.body()!!
                    when (val code = resp.code) {
                        1000 -> loginView.onLoginSuccess(code, resp)
                        else -> loginView.onLoginFailure(code)
                    }
                }
            }

            override fun onFailure(call: Call<StringResultResponse>, t: Throwable) {
                Log.d(TAG, "login() / onFailure() " + t.message.toString())
            }
        })

        Log.d("TAG", "login() 메소드 실행 완료")
    }

    fun kakaoLogin(accessToken: String) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.kakaoLogin(accessToken).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.body() != null) {
                    Log.d("kakao-login-success", response.toString())

                    val resp: LoginResponse = response.body()!!
                    when (val code = resp.code) {
                        1000 -> loginView.onKakaoLoginSuccess(code, resp)
                        else -> loginView.onLoginFailure(code)
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("KAKAO-LOGIN/FAILURE", t.message.toString())
            }

        })
    }

    fun userInfo(accessToken: String, refreshToken: String) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.userInfo(accessToken, refreshToken).enqueue(object : Callback<UserInfoResponse> {
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

    fun nickCheck(nick: String) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.joinNickCheck(nick).enqueue(object: Callback<JoinCheckResponse> {
            override fun onResponse(call: Call<JoinCheckResponse>, response: Response<JoinCheckResponse>) {
                if(response.body() != null) {
                    Log.d("NICKNAMECHECK/SUCCESS", response.toString())

                    val resp: JoinCheckResponse = response.body()!!
                    when(resp.code) {
                        1000 -> nickCheckView.onNickCheckSuccess()
                        else -> nickCheckView.onNickCheckFailure()
                    }
                }
            }
            override fun onFailure(call: Call<JoinCheckResponse>, t: Throwable) {
                Log.d("JOINNICKCHECK/FAILURE", t.message.toString())
            }
        })

        Log.d("NICKCHECK()/", "메소드")
    }

    fun emailSend(email : String) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.emailSend(email).enqueue(object : Callback<EmailResponse>{
            override fun onResponse(call: Call<EmailResponse>, response: Response<EmailResponse>) {
                if(response.body() != null) {
                    Log.d("emailSend-success", response.toString())

                    val response: EmailResponse = response.body()!!
                    when(response.code) {
                        1000 -> joinCheckView.onEmailSendSuccess(response.result)
                        else -> joinCheckView.onEmailSendFailure()
                    }
                }
            }
            override fun onFailure(call: Call<EmailResponse>, t: Throwable) {
                Log.d("email-send-fail", t.message.toString())
            }

        })
    }

    fun emailCertificate(code : String, etCode : String) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.emailCertificate(code, etCode).enqueue(object : Callback<EmailResponse>{
            override fun onResponse(call: Call<EmailResponse>, response: Response<EmailResponse>) {
                if(response.body() != null) {
                    Log.d("emailCert-success", response.toString())

                    val response: EmailResponse = response.body()!!
                    when(response.code) {
                        1000 -> joinCheckView.onEmailCertificateSuccess()
                        else -> joinCheckView.onEmailCertificateFailure(response.code)
                    }
                }
            }

            override fun onFailure(call: Call<EmailResponse>, t: Throwable) {
                Log.d("email-cert-fail", t.message.toString())
            }

        })
    }

    fun deleteUser(accessToken: String, refreshToken: String) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.deleteUser(accessToken, refreshToken)
            .enqueue(object : Callback<UserDeleteResponse> {
                override fun onResponse(
                    call: Call<UserDeleteResponse>,
                    response: Response<UserDeleteResponse>
                ) {
                    if (response.body() != null) {
                        Log.d("USERINFO/성공", response.toString())

                        val resp: UserDeleteResponse = response.body()!!
                        when (val code = resp.code) {
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

    // Access Token 재발급
    fun getReAccessToken(accessToken: String, refreshToken: String) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.getReAccessToken(accessToken, refreshToken)
            .enqueue(object : Callback<ReAccessTokenResponse> {
                override fun onResponse(
                    call: Call<ReAccessTokenResponse>,
                    response: Response<ReAccessTokenResponse>
                ) {
                    if (response.body() != null) {
                        Log.d("getReAccessToken/성공", response.toString())

                        val resp: ReAccessTokenResponse = response.body()!!
                        when (val code = resp.code) {
                            1000 -> reAccessTokenView.onGetReAccessTokenSuccess(resp)
                            else -> reAccessTokenView.onGetReAccessTokenFailure()
                        }
                    }
                }

                override fun onFailure(call: Call<ReAccessTokenResponse>, t: Throwable) {
                    Log.d("getReAccessToken/실패", t.message.toString())
                }
            })
    }

    // Nick Change
    fun nickChange(nickChangeRequest: NickChangeRequest, accessToken: String, refreshToken: String) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.nickChange(nickChangeRequest, accessToken, refreshToken)
            .enqueue(object : Callback<NickChangeResponse> {
                override fun onResponse(
                    call: Call<NickChangeResponse>,
                    response: Response<NickChangeResponse>
                ) {
                    if(response.body() != null) {
                        Log.d("nickChange()", response.body().toString())

                        val resp: NickChangeResponse = response.body()!!
                        when(val code = resp.code) {
                            1000 -> nickChangeView.onNickChangeSuccess(resp)
                            else -> nickChangeView.onNickChangeFailure()
                        }
                    }
                }

                override fun onFailure(call: Call<NickChangeResponse>, t: Throwable) {
                    Log.d("nickChange() 실패", t.message.toString())
                }
            })
    }
}