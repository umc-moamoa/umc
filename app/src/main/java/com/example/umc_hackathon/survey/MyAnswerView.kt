package com.example.umc_hackathon.survey

interface MyAnswerView {
    fun onGetMyAnswerSuccess(myAnswerResponse: MyAnswerResponse)
    fun onGetMyAnswerFailure()
}