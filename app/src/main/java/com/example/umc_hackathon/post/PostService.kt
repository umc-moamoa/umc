package com.example.umc_hackathon.post

import android.util.Log
import com.example.umc_hackathon.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostService {
    private lateinit var postListView: PostListView
    private lateinit var popularSurveyView: PopularSurveyView
    private lateinit var waitingSurveyView: WaitingSurveyView
    private lateinit var interestSurveyListView: InterestSurveyListView
    private lateinit var participatedSurveyView: ParticipatedSurveyView
    private lateinit var mySurveyView: MySurveyView
    private lateinit var postDetailView: PostDetailView
    private lateinit var myPointView: MyPointView

    fun setPostListView(postListView: PostListView) {
        this.postListView = postListView
    }

    fun setPopularSurveyView(popularSurveyView: PopularSurveyView) {
        this.popularSurveyView = popularSurveyView
    }

    fun setWaitingSurveyView(waitingSurveyView: WaitingSurveyView) {
        this.waitingSurveyView = waitingSurveyView
    }

    fun setInterestSurveyListView(interestSurveyListView: InterestSurveyListView) {
        this.interestSurveyListView = interestSurveyListView
    }

    fun setParticipatedSurveyView(participatedSurveyView: ParticipatedSurveyView) {
        this.participatedSurveyView = participatedSurveyView
    }

    fun setMySurveyView(mySurveyView: MySurveyView) {
        this.mySurveyView = mySurveyView
    }

    fun setPostDetailView(postDetailView: PostDetailView) {
        this.postDetailView = postDetailView
    }

    fun setMyPointView(myPointView: MyPointView) {
        this.myPointView = myPointView
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

    fun getPopularSurvey() {
        val postService = getRetrofit().create(PostRetrofitInterface::class.java)

        postService.getPopularSurvey().enqueue(object: Callback<PostListResponse> {
            override fun onResponse(call: Call<PostListResponse>, response: Response<PostListResponse>) {
                if(response.body() != null) {
                    Log.d("getPopularSurvey()", " / " + response.body())
                    val postList: PostListResponse = response.body()!!

                    when(postList.code) {
                        1000 -> popularSurveyView.onGetPopularSurveySuccess(postList)
                        else -> popularSurveyView.onGetPopularSurveyFailure()
                    }
                }
            }

            override fun onFailure(call: Call<PostListResponse>, t: Throwable) {
                Log.d("getPopularSurvey() 실패", " / " + t.message.toString())
            }
        })
    }

    fun getWaitingSurvey() {
        val postService = getRetrofit().create(PostRetrofitInterface::class.java)

        postService.getWaitingSurvey().enqueue(object: Callback<PostListResponse> {
            override fun onResponse(call: Call<PostListResponse>, response: Response<PostListResponse>) {
                if(response.body() != null) {
                    Log.d("getWaitingSurvey()", " / " + response.body())
                    val postList: PostListResponse = response.body()!!

                    when(postList.code) {
                        1000 -> waitingSurveyView.onGetWaitingSurveySuccess(postList)
                        else -> waitingSurveyView.onGetWaitingSurveyFailure()
                    }
                }
            }

            override fun onFailure(call: Call<PostListResponse>, t: Throwable) {
                Log.d("getWaitingSurvey() 실패", " / " + t.message.toString())
            }
        })
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

    fun getMySurvey(jwt: String) {
        val postService = getRetrofit().create(PostRetrofitInterface::class.java)

        postService.getMySurvey(jwt).enqueue(object: Callback<MySurveyResponse> {
            override fun onResponse(call: Call<MySurveyResponse>, response: Response<MySurveyResponse>) {
                if(response.body() != null) {
                    Log.d("getMySurvey()", " / " + response.body())
                    val mySurveyList: MySurveyResponse = response.body()!!

                    when(mySurveyList.code) {
                        1000 -> mySurveyView.onGetMySurveyViewSuccess(mySurveyList)
                        else -> mySurveyView.onGetMySurveyViewFailure()
                    }
                }
            }

            override fun onFailure(call: Call<MySurveyResponse>, t: Throwable) {
                Log.d("getMySurvey()", " 실패 / " + t.message.toString())
            }
        })

        Log.d("getMySurvey()", " / PostService에서 메소드")
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

    fun likePost(postId: Long, jwt: String) {
        val postService = getRetrofit().create(PostRetrofitInterface::class.java)

        postService.likePost(postId, jwt).enqueue(object: Callback<LikeResponse> {
            override fun onResponse(call: Call<LikeResponse>, response: Response<LikeResponse>) {
                if(response.body() != null) {
                    Log.d("getLikePost()", " / " + response.body())
                    val likeResponse: LikeResponse = response.body()!!

                    when(likeResponse.code) {
                        1000 -> postDetailView.onLikeSuccess()
                        else -> postDetailView.onLikeFailure(likeResponse)
                    }
                }
            }

            override fun onFailure(call: Call<LikeResponse>, t: Throwable) {
                Log.d("getLikePost()", " 실패 / " + t.message.toString())
            }
        })
    }

    fun dislikePost(postId: Long, jwt: String) {
        val postService = getRetrofit().create(PostRetrofitInterface::class.java)

        postService.dislikePost(postId, jwt).enqueue(object: Callback<LikeResponse> {
            override fun onResponse(call: Call<LikeResponse>, response: Response<LikeResponse>) {
                if(response.body() != null) {
                    Log.d("dislikePost()", " / " + response.body())
                    val likeResponse: LikeResponse = response.body()!!

                    when(likeResponse.code) {
                        1000 -> postDetailView.onDislikeSuccess()
                        else -> postDetailView.onDislikeFailure(likeResponse)
                    }
                }
            }

            override fun onFailure(call: Call<LikeResponse>, t: Throwable) {
                Log.d("dislikePost()", " 실패 / " + t.message.toString())
            }
        })
    }

    fun getMyPoint(jwt: String) {
        val postService = getRetrofit().create(PostRetrofitInterface::class.java)

        postService.getMyPoint(jwt).enqueue(object: Callback<MyPointResponse> {
            override fun onResponse(call: Call<MyPointResponse>, response: Response<MyPointResponse>) {
                if(response.body() != null) {
                    Log.d("getMyPoint()", " / " + response.body())
                    val myPointList: MyPointResponse = response.body()!!

                    when(myPointList.code) {
                        1000 -> myPointView.onGetMyPointSuccess(myPointList)
                        else -> myPointView.onGetMyPointFailure()
                    }
                }
            }

            override fun onFailure(call: Call<MyPointResponse>, t: Throwable) {
                Log.d("getMyPoint()", " 실패 / " + t.message.toString())
            }
        })

        Log.d("getMyPoint()", " / PostService에서 메소드")
    }
}