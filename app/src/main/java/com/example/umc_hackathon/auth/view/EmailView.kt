package com.example.umc_hackathon.auth.view

interface EmailView {
    fun onEmailSendSuccess(result : String)
    fun onEmailSendFailure()
    fun onEmailCertificateSuccess()
    fun onEmailCertificateFailure(code: Int)
}