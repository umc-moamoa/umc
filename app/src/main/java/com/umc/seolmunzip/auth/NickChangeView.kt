package com.umc.seolmunzip.auth

interface NickChangeView {
    fun onNickChangeSuccess(resp: NickChangeResponse)
    fun onNickChangeFailure()
}