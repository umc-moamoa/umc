package com.example.umc_hackathon.post


interface MyPointView {
    fun onGetMyPointSuccess(myPointList: MyPointResponse)
    fun onGetMyTotalPointSuccess(code: Int, result: MyPointList)
    fun onGetMyPointFailure()
}