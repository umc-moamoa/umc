package com.example.umc_hackathon.post

interface PopularSurveyView {
    fun onGetPopularSurveySuccess(postList: PostListResponse)
    fun onGetPopularSurveyFailure()
}