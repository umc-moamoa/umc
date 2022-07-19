package com.example.umc_hackathon

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response

class PostService {
    private lateinit var postView: PostView

    fun setPostView(postView: PostView) {
        this.postView = postView
    }

    fun allPost() {
        val allPostService = getRetrofit().create(PostRetrofitInterface::class.java)

        allPostService.getAllPosts().enqueue(object : retrofit2.Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val postList = response.body()!!
                    postView.onAllPostSuccess(postList)
                    Log.d("postlist-retrofit", postList.toString())
                }

            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("postlist-retrofit", t.toString())
            }
        })

    }
}