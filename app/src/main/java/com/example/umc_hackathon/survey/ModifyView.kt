package com.example.umc_hackathon.survey

interface ModifyView {
    fun onFormModifySuccess(modifyResponse: ModifyResponse)
    fun onFormModifyFailure()
}