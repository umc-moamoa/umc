package com.example.umc_hackathon.post.result

interface ResultView {
    fun onGetResultSuccess(result: DetailResult)
    fun onGetResultFailure()
}