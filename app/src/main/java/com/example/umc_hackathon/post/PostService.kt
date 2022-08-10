package com.example.umc_hackathon.post

import android.util.Log
import android.widget.Toast
import com.example.umc_hackathon.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostService {
    private lateinit var postListView: PostListView
    private lateinit var interestSurveyListView: InterestSurveyListView

    fun setPostListView(postListView: PostListView) {
        this.postListView = postListView
    }

    fun setInterestSurveyListView(interestSurveyListView: InterestSurveyListView) {
        this.interestSurveyListView = interestSurveyListView
    }

    fun getPostList(category: Long) {
        val postService = getRetrofit().create(PostRetrofitInterface::class.java)

        postService.getPostList(category).enqueue(object: Callback<PostListResponse> {
            override fun onResponse(call: Call<PostListResponse>, response: Response<PostListResponse>) {
               if(response.body() != null) {
                   Log.d("getPostList()", " / " + response.body())
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

    fun getInterestSurveyList() {
        val postService = getRetrofit().create(PostRetrofitInterface::class.java)

        postService.getInterestSurveyList().enqueue(object: Callback<PostListResponse> {
            override fun onResponse(call: Call<PostListResponse>, response: Response<PostListResponse>) {
                if(response.body() != null) {
                    Log.d("getInterestSurveyList()", " / " + response.body())
                    val postList: PostListResponse = response.body()!!

                    when(postList.code) {
                        1000 -> interestSurveyListView.onGetInterestSurveyListSuccess(postList)
                        else -> interestSurveyListView.onGetInterestSurveyListFailure()
                    }
                }
            }

            override fun onFailure(call: Call<PostListResponse>, t: Throwable) {
                Log.d("getInterestSurveyList()", " 실패 / " + t.message.toString())
            }
        })
    }
}