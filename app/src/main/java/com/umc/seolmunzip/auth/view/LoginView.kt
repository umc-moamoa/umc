package com.umc.seolmunzip.auth.view

import com.umc.seolmunzip.auth.dto.LoginResponse
import com.umc.seolmunzip.post.StringResultResponse

interface LoginView {
    fun onLoginSuccess(code: Int, result: StringResultResponse)
    fun onLoginFailure(code: Int)

    fun onKakaoLoginSuccess(code: Int, result:LoginResponse)
}