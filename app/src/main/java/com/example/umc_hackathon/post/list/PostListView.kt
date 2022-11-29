package com.example.umc_hackathon.post.list

interface PostListView {
    fun onGetPostListSuccess(postList: PostListResponse)
    fun onGetPostListFailure()
}