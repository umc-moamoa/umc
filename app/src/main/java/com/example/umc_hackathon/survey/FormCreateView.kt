package com.example.umc_hackathon.survey

interface FormCreateView {
    fun onFormCreateSuccess()
    fun onFormCreateFailure(code: Int)
}