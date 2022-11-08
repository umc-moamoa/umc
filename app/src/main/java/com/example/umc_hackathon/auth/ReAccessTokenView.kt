package com.example.umc_hackathon.auth

interface ReAccessTokenView {
    fun onGetReAccessTokenSuccess(res: ReAccessTokenResponse)
    fun onGetReAccessTokenFailure()
}