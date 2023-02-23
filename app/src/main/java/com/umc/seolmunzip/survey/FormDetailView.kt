package com.umc.seolmunzip.survey

// 설문조사 문항 확인 & 제출
interface FormDetailView {
    fun onFormDetailSuccess(formDetailResponse: FormDetailResponse)
    fun onFormDetailFailure()

    fun onFormSubmitSucess()
    fun onFormSubitFailure()
}