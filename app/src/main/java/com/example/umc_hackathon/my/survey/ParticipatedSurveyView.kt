package com.example.umc_hackathon.my.survey

import com.example.umc_hackathon.post.list.PostListResponse

interface ParticipatedSurveyView {
    fun onGetParticipatedSurveySuccess(postList: PostListResponse)
    fun onGetParticipatedSurveyFailure()
}