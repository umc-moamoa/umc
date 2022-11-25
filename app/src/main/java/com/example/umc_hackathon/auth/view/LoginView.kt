package com.example.umc_hackathon.auth.view

import com.example.umc_hackathon.auth.dto.LoginResponse

interface LoginView {
    fun onLoginSuccess(code: Int, result: LoginResponse)
    fun onLoginFailure(code: Int)
}