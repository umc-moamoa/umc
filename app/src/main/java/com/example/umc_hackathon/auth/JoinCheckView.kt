package com.example.umc_hackathon.auth

interface JoinCheckView {
    fun onJoinIdCheckSuccess()
    fun onJoinIdCheckFailure()
    fun onJoinNickCheckSuccess()
    fun onJoinNickCheckFailure()
}