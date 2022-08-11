package com.example.umc_hackathon.post

interface ParticipatedSurveyView {
    fun onGetParticipatedSurveySuccess(postList: PostListResponse)
    fun onGetParticipatedSurveyFailure()
}