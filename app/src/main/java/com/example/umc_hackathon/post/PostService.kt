package com.example.umc_hackathon.post

import android.util.Log
import android.widget.Toast
import com.example.umc_hackathon.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostService {
    private lateinit var postListView: PostListView

    fun setPostListView(postListView: PostListView) {
        this.postListView = postListView
    }

    fun getPostList(category: Long) {
        val postService = getRetrofit().create(PostRetrofitInterface::class.java)

        postService.getPostList(category).enqueue(object: Callback<PostListResponse> {
            override fun onResponse(call: Call<PostListResponse>, response: Response<PostListResponse>) {
               if(response.body() != null) {
                   Log.d("postService()", " / " + response.body())
                   val postList: PostListResponse = response.body()!!

                   when(postList.code) {
                       1000 -> postListView.onGetPostListSuccess(postList)
                       else -> postListView.onGetPostListFailure()
                   }
               }
            }

            override fun onFailure(call: Call<PostListResponse>, t: Throwable) {
                Log.d("getPostList() 실패 /  ", t.message.toString())
            }
        })
        
        Log.d("getPostList() / ", "PostService에서 메소드")
    }
}