package com.example.umc_hackathon.post

interface PostListView {
    fun onGetPostListSuccess(postList: PostListResponse)
    fun onGetPostListFailure()
}