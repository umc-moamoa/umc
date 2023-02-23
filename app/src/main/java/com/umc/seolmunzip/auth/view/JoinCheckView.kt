package com.umc.seolmunzip.auth.view

interface JoinCheckView {
    fun onJoinIdCheckSuccess()
    fun onJoinIdCheckFailure()
    fun onJoinNickCheckSuccess()
    fun onJoinNickCheckFailure()
}