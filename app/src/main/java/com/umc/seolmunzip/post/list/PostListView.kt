package com.umc.seolmunzip.post.list

interface PostListView {
    fun onGetPostListSuccess(postList: PostListResponse)
    fun onGetPostListFailure()
}