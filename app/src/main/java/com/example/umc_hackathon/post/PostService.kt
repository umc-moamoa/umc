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

        getAllPostService.getAllPosts(category).enqueue(object : retrofit2.Callback<PostListResponse> {
            override fun onResponse(call: Call<PostListResponse>, response: Response<PostListResponse>) {
                if (response.isSuccessful) {
                    val postList = response.body()!!
                    when(postList.code) {
                        4000 -> Log.d("GET_ALL_POST/", "데이터베이스 연결에 실패하였습니다.")
                        2012 -> Log.d("GET_ALL_POST/", "카테고리 아이디 값을 확인해주세요.")
                        1000 -> {
                            postView.onGetAllPostSuccess(postList)
                            Log.d("GET_ALL_POST/", "성공했습니다.")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<PostListResponse>, t: Throwable) {
                Log.d("[F]GET_ALL_POST/ ", t.toString())
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
                    Log.d("GET_POST_DETAIL/", postId.toString())
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("[F]GET_POST_DETAIL/", t.toString())
            }
        })
    }
}