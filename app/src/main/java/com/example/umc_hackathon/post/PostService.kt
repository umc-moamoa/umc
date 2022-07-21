package com.example.umc_hackathon.post

import android.util.Log
import com.example.umc_hackathon.getRetrofit
import retrofit2.Call
import retrofit2.Response

class PostService {
    private lateinit var postView: PostView

    fun setPostView(postView: PostView) {
        this.postView = postView
    }

    fun getAllPost(category: Int) {
        val getAllPostService = getRetrofit().create(PostRetrofitInterface::class.java)

        getAllPostService.getAllPosts(category).enqueue(object : retrofit2.Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val postList = response.body()!!
                    postView.onGetAllPostSuccess(postList)
                    Log.d("postlist-retrofit", postList.toString())
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("postlist-retrofit", t.toString())
            }
        })

    }

    fun getPostDetail(postId: Int) {
        val postDetailService = getRetrofit().create(PostRetrofitInterface::class.java)

        postDetailService.getPostDetail(postId).enqueue(object : retrofit2.Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val post = response.body()!!
                    postView.onGetPostDetail(post)
                    Log.d("post-detail", postId.toString())
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("post-detail-fail", t.toString())
            }
        })
    }
}