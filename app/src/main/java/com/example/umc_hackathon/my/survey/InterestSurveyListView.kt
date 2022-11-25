package com.example.umc_hackathon.my.survey

import com.example.umc_hackathon.post.list.PostListResponse

interface InterestSurveyListView {
    fun onGetInterestSurveyListSuccess(postList: PostListResponse)
    fun onGetInterestSurveyListFailure(result: PostListResponse)
}