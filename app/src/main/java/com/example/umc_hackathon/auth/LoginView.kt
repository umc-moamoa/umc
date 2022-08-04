package com.example.umc_hackathon.auth

interface LoginView {
    fun onLoginSuccess(code: Int, result: LoginResult)
    fun onLoginFailure(code: Int)
}