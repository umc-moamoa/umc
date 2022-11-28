package com.example.umc_hackathon.auth.view

import com.example.umc_hackathon.auth.dto.LoginResponse
import com.example.umc_hackathon.post.StringResultResponse

interface LoginView {
    fun onLoginSuccess(code: Int, result: StringResultResponse)
    fun onLoginFailure(code: Int)

    fun onKakaoLoginSuccess(code: Int, result:LoginResponse)
}