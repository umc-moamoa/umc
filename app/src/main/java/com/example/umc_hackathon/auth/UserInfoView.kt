package com.example.umc_hackathon.auth

interface UserInfoView {
    fun onUserInfoSuccess(code: Int, result: UserInfoResult)
    fun onUserInfoFailure(code: Int)
}