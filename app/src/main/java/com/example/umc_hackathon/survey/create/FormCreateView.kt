package com.example.umc_hackathon.survey.create

interface FormCreateView {
    fun onFormCreateSuccess()
    fun onFormCreateFailure(code: Int)
}