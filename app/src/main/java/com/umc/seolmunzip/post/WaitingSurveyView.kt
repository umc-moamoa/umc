package com.umc.seolmunzip.post

import com.umc.seolmunzip.post.list.PostListResponse

interface WaitingSurveyView {
    fun onGetWaitingSurveySuccess(postList: PostListResponse)
    fun onGetWaitingSurveyFailure()
}