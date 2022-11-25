package com.example.umc_hackathon.post.detail

import com.example.umc_hackathon.post.LikeResponse
import com.example.umc_hackathon.post.StringResultResponse

interface PostDetailView {
    fun onGetPostDetailSuccess(result: PostDetailResult)
    fun onGetPostDetailFailure(result: PostDetailResponse)

    fun onLikeSuccess()
    fun onLikeFailure(result: LikeResponse)

    fun onDislikeSuccess()
    fun onDislikeFailure(result: StringResultResponse)

    fun onDeleteSuccess()
    fun onDeleteFailure(result: StringResultResponse)

    fun onGetShareLinkSuccess(result: String)
    fun onGetShareLinkFailure()
}