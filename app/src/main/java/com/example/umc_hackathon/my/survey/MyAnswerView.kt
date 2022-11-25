package com.example.umc_hackathon.my.survey

interface MyAnswerView {
    fun onGetMyAnswerSuccess(myAnswerResponse: MyAnswerResponse)
    fun onGetMyAnswerFailure()
}