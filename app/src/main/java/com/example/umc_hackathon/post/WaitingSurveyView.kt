package com.example.umc_hackathon.post

import com.example.umc_hackathon.post.list.PostListResponse

interface WaitingSurveyView {
    fun onGetWaitingSurveySuccess(postList: PostListResponse)
    fun onGetWaitingSurveyFailure()
}