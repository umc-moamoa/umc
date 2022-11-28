package com.example.umc_hackathon.post

import com.example.umc_hackathon.post.list.PostListResponse

interface PopularSurveyView {
    fun onGetPopularSurveySuccess(postList: PostListResponse)
    fun onGetPopularSurveyFailure()
}