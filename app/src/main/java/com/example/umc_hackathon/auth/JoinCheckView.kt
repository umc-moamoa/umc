package com.example.umc_hackathon.auth

interface JoinCheckView {
    fun onJoinIdCheckSuccess()
    fun onJoinIdCheckFailure()
    fun onEmailSendSuccess()
    fun onEmailSendFailure()
    fun onEmailCertificateSuccess()
    fun onEmailCertificateFailure(code: Int)
    fun onJoinNickCheckSuccess()
    fun onJoinNickCheckFailure()
}