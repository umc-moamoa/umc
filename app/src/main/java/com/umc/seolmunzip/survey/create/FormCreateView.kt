package com.umc.seolmunzip.survey.create

interface FormCreateView {
    fun onFormCreateSuccess()
    fun onFormCreateFailure(code: Int)
}