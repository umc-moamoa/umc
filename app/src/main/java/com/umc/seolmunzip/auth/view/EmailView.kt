package com.umc.seolmunzip.auth.view

interface EmailView {
    fun onEmailSendSuccess(result : String)
    fun onEmailSendFailure()
    fun onEmailCertificateSuccess()
    fun onEmailCertificateFailure(code: Int)
}