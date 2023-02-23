package com.umc.seolmunzip.post

import com.umc.seolmunzip.post.list.PostListResponse

interface PopularSurveyView {
    fun onGetPopularSurveySuccess(postList: PostListResponse)
    fun onGetPopularSurveyFailure()
}