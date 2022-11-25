package com.example.umc_hackathon.post

import okhttp3.ResponseBody
import retrofit2.Response

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