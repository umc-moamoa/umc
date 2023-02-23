package com.umc.seolmunzip.auth.view

import com.umc.seolmunzip.auth.dto.ReAccessTokenResponse

interface ReAccessTokenView {
    fun onGetReAccessTokenSuccess(res: ReAccessTokenResponse)
    fun onGetReAccessTokenFailure()
}