package com.example.umc_hackathon.survey

// 설문조사 문항 확인
interface FormDetailView {
    fun onFormDetailSuccess(formDetailResponse: FormDetailResponse)
    fun onFormDetailFailure()
}