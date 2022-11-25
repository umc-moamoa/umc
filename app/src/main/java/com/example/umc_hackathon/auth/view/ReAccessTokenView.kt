package com.example.umc_hackathon.auth.view

import com.example.umc_hackathon.auth.dto.ReAccessTokenResponse

interface ReAccessTokenView {
    fun onGetReAccessTokenSuccess(res: ReAccessTokenResponse)
    fun onGetReAccessTokenFailure()
}