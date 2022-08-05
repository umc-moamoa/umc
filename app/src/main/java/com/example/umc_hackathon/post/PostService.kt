package com.example.umc_hackathon.post

import android.util.Log
import com.example.umc_hackathon.auth.UserInfoResponse
import com.example.umc_hackathon.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostService {
    private lateinit var postListView: PostListView

    fun setPostListView(postListView: PostListView) {
        this.postListView = postListView
    }

    fun getPostList(postListRequest: PostListRequest) {
        val postService = getRetrofit().create(PostRetrofitInterface::class.java)

        postService.getPostList(postListRequest.categoryId).enqueue(object: Callback<PostListResponse> {
            override fun onResponse(call: Call<PostListResponse>, response: Response<PostListResponse>) {
                if(response.body() != null) {
                    Log.d("getPostList()/성공", response.toString())

                    val resp: PostListResponse = response.body()!!
                    when(val code = resp.code) {
                        1000 -> postListView.onPostListSuccess(code, resp.result!!)
                        else -> postListView.onPostListFailure(code, resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<PostListResponse>, t: Throwable) {
                Log.d("getPostList()/실패", t.message.toString())
            }
        })
        
        Log.d("getPostList()/", "메소드")
    }
}