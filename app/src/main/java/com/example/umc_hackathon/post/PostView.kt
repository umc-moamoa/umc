package com.example.umc_hackathon.post

interface PostView {
    fun onGetAllPostSuccess(postList: PostListResponse)
    fun onGetPostDetail(post: Post)
}