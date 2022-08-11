package com.example.umc_hackathon.post

interface PostDetailView {
    fun onGetPostDetailSuccess(result: PostDetailResult)
    fun onGetPostDetailFailure()

    fun onLikeSuccess()
    fun onLikeFailure(result: LikeResponse)
}