package com.example.umc_hackathon.post

interface PostListView {
    fun onPostListSuccess(code: Int, result: PostListResult)
    fun onPostListFailure(code: Int, message: String)
}