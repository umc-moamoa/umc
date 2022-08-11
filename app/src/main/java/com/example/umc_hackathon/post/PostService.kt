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
    private lateinit var participatedSurveyView: ParticipatedSurveyView
    private lateinit var postDetailView: PostDetailView

    fun setPostListView(postListView: PostListView) {
        this.postListView = postListView
    }

    fun setInterestSurveyListView(interestSurveyListView: InterestSurveyListView) {
        this.interestSurveyListView = interestSurveyListView
    }

    fun setParticipatedSurveyView(participatedSurveyView: ParticipatedSurveyView) {
        this.participatedSurveyView = participatedSurveyView
    }

    fun setPostDetailView(postDetailView: PostDetailView) {
        this.postDetailView = postDetailView
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

    fun getInterestSurveyList(jwt: String) {
        val postService = getRetrofit().create(PostRetrofitInterface::class.java)

        postService.getInterestSurveyList(jwt).enqueue(object: Callback<PostListResponse> {
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

        Log.d("getInterestSurveyList()", " / PostService에서 메소드")
    }

    fun getParticipatedSurvey(jwt: String) {
        val postService = getRetrofit().create(PostRetrofitInterface::class.java)

        postService.getParticipatedSurvey(jwt).enqueue(object: Callback<PostListResponse> {
            override fun onResponse(call: Call<PostListResponse>, response: Response<PostListResponse>) {
                if(response.body() != null) {
                    Log.d("getParticipatedSurvey()", " / " + response.body())
                    val postList: PostListResponse = response.body()!!

                    when(postList.code) {
                        1000 -> participatedSurveyView.onGetParticipatedSurveySuccess(postList)
                        else -> participatedSurveyView.onGetParticipatedSurveyFailure()
                    }
                }
            }

            override fun onFailure(call: Call<PostListResponse>, t: Throwable) {
                Log.d("getParticipatedSurvey()", " 실패 / " + t.message.toString())
            }
        })

        Log.d("getParticipatedSurvey()", " / PostService에서 메소드")
    }

    fun getPostDetail(postId: Long, jwt: String) {
        val postService = getRetrofit().create(PostRetrofitInterface::class.java)

        postService.getPostDetail(postId, jwt).enqueue(object: Callback<PostDetailResponse> {
            override fun onResponse(call: Call<PostDetailResponse>, response: Response<PostDetailResponse>) {
                if(response.body() != null) {
                    Log.d("getPostDetail()", " / " + response.body())
                    val postDetail: PostDetailResponse = response.body()!!

                    when(postDetail.code) {
                        1000 -> postDetailView.onGetPostDetailSuccess(postDetail.result!!)
                        else -> postDetailView.onGetPostDetailFailure()
                    }
                }
            }

            override fun onFailure(call: Call<PostDetailResponse>, t: Throwable) {
                Log.d("getPostDetail()", " 실패 / " + t.message.toString())
            }
        })

        Log.d("getPostDetail() / ", " / PostService에서 메소드")
    }
}