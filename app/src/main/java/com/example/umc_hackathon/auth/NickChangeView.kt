package com.example.umc_hackathon.auth

interface NickChangeView {
    fun onNickChangeSuccess(resp: NickChangeResponse)
    fun onNickChangeFailure()
}