package com.example.umc_hackathon.auth

interface LoginView {
    fun onLoginSuccess(code: Int, result: LoginResponse)
    fun onLoginFailure(code: Int)
}