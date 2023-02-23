package com.umc.seolmunzip.my.survey

interface MyAnswerView {
    fun onGetMyAnswerSuccess(myAnswerResponse: MyAnswerResponse)
    fun onGetMyAnswerFailure()
}