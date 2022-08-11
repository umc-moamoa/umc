package com.example.umc_hackathon.post

interface InterestSurveyListView {
    fun onGetInterestSurveyListSuccess(postList: PostListResponse)
    fun onGetInterestSurveyListFailure()
}