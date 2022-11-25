package com.example.umc_hackathon.post.modify

interface ModifyView {
    fun onFormModifySuccess(modifyResponse: ModifyResponse)
    fun onFormModifyFailure()
}