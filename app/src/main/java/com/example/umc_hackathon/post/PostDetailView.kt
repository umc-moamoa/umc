package com.example.umc_hackathon.post

interface PostDetailView {
    fun onGetPostDetailSuccess(result: PostDetailResult)
    fun onGetPostDetailFailure(result: PostDetailResponse)

    fun onLikeSuccess()
    fun onLikeFailure(result: LikeResponse)

    fun onDislikeSuccess()
    fun onDislikeFailure(result: StringResultResponse)

    fun onDeleteSuccess()
    fun onDeleteFailure(result: StringResultResponse)
}