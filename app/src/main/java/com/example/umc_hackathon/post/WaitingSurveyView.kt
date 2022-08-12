package com.example.umc_hackathon.post

interface WaitingSurveyView {
    fun onGetWaitingSurveySuccess(postList: PostListResponse)
    fun onGetWaitingSurveyFailure()
}