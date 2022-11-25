package com.example.umc_hackathon.auth.view

interface JoinCheckView {
    fun onJoinIdCheckSuccess()
    fun onJoinIdCheckFailure()
    fun onEmailSendSuccess(result : String)
    fun onEmailSendFailure()
    fun onEmailCertificateSuccess()
    fun onEmailCertificateFailure(code: Int)
    fun onJoinNickCheckSuccess()
    fun onJoinNickCheckFailure()
}