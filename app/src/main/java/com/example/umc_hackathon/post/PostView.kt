package com.example.umc_hackathon.post

interface PostView {
    fun onGetAllPostSuccess(postList: List<Post>)

    fun onGetPostDetail(post: Post)
}