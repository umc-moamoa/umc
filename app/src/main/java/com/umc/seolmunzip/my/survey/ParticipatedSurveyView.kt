package com.umc.seolmunzip.my.survey

import com.umc.seolmunzip.post.list.PostListResponse

interface ParticipatedSurveyView {
    fun onGetParticipatedSurveySuccess(postList: PostListResponse)
    fun onGetParticipatedSurveyFailure()
}