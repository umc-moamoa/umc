package com.example.umc_hackathon.auth.view

interface JoinCheckView {
    fun onJoinIdCheckSuccess()
    fun onJoinIdCheckFailure()
    fun onJoinNickCheckSuccess()
    fun onJoinNickCheckFailure()
}