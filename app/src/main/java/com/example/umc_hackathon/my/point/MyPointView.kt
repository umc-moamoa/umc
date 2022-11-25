package com.example.umc_hackathon.my.point


interface MyPointView {
    fun onGetMyRecentPointSuccess(myPointList: MyPointResponse)
    fun onGetMyFormerPointSuccess(myPointList: MyPointResponse)
    fun onGetMyTotalPointSuccess(code: Int, result: MyPointList)
    fun onGetMyPointFailure(result: MyPointResponse)
}