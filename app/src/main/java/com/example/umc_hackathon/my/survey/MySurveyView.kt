package com.example.umc_hackathon.my.survey

interface MySurveyView {
    fun onGetMySurveyViewSuccess(mySurveyList: MySurveyResponse)
    fun onGetMySurveyViewFailure(mySurveyList: MySurveyResponse)
}