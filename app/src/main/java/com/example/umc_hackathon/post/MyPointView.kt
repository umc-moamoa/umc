package com.example.umc_hackathon.post

interface MyPointView {
    fun onGetMyPointSuccess(myPointList: MyPointResponse)
    fun onGetMyPointFailure()
}