package com.umc.seolmunzip.my.survey

import com.umc.seolmunzip.post.list.PostListResponse

interface InterestSurveyListView {
    fun onGetInterestSurveyListSuccess(postList: PostListResponse)
    fun onGetInterestSurveyListFailure(result: PostListResponse)
}