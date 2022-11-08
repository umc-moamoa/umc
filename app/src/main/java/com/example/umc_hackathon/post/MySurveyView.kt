package com.example.umc_hackathon.post

interface MySurveyView {
    fun onGetMySurveyViewSuccess(mySurveyList: MySurveyResponse)
    fun onGetMySurveyViewFailure(mySurveyList: MySurveyResponse)
}