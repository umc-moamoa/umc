package com.umc.seolmunzip.post.modify

interface ModifyView {
    fun onFormModifySuccess(modifyResponse: ModifyResponse)
    fun onFormModifyFailure()
}