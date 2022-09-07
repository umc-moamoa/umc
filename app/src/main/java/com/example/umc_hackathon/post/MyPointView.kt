package com.example.umc_hackathon.post


interface MyPointView {
    fun onGetMyRecentPointSuccess(myPointList: MyPointResponse)
    fun onGetMyFormerPointSuccess(myPointList: MyPointResponse)
    fun onGetMyTotalPointSuccess(code: Int, result: MyPointList)
    fun onGetMyPointFailure()
}