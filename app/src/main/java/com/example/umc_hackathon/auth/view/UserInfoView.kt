package com.example.umc_hackathon.auth.view

import com.example.umc_hackathon.auth.dto.UserInfoResult

interface UserInfoView {
    fun onUserInfoSuccess(code: Int, result: UserInfoResult)
    fun onUserInfoFailure(code: Int)
}