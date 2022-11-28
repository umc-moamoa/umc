package com.example.umc_hackathon.survey.result

interface ResultView {
    fun onGetResultSuccess(result: DetailResult)
    fun onGetResultFailure(message: String)

    fun onGetDetailIdSuccess(result: DetailIdResponse)
    fun onGetDetailIdFailure(message: String)
}