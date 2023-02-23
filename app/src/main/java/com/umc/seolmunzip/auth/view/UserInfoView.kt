package com.umc.seolmunzip.auth.view

import com.umc.seolmunzip.auth.dto.UserInfoResult

interface UserInfoView {
    fun onUserInfoSuccess(code: Int, result: UserInfoResult)
    fun onUserInfoFailure(code: Int)
}