package com.example.umc_hackathon.post

interface RecentMyPointView {
    fun onGetRecentMyPointSuccess(myPointList: MyPointList)
    fun onGetRecentMyPointFailure()
}