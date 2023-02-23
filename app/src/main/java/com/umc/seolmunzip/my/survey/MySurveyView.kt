package com.umc.seolmunzip.my.survey

interface MySurveyView {
    fun onGetMySurveyViewSuccess(mySurveyList: MySurveyResponse)
    fun onGetMySurveyViewFailure(mySurveyList: MySurveyResponse)
}