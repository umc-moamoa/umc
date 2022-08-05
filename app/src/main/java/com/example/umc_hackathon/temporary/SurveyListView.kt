package com.example.umc_hackathon.temporary

interface SurveyListView {
    fun onGetSurveyListSuccess(code: Int, result: SurveyListResult )
    fun onGetSurveyListFailure(code: Int, message: String)
}