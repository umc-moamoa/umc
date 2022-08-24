package com.example.umc_hackathon.post.result

interface ResultView {
    fun onGetResultSuccess(result: DetailResult)
    fun onGetResultFailure(message: String)

    fun onGetDetailIdSuccess(result: DetailIdResponse)
    fun onGetDetailIdFailure(message: String)
}