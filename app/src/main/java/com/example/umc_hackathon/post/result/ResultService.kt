package com.example.umc_hackathon.post.result

import android.util.Log
import com.example.umc_hackathon.getRetrofit
import com.example.umc_hackathon.post.PostRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultService {
    private lateinit var resultView: ResultView

    fun setResultView(resultView: ResultView) {
        this.resultView = resultView
    }

    fun getResult(postDetailId: Long) {
        val resultService = getRetrofit().create(PostRetrofitInterface::class.java)

        resultService.getResults(postDetailId).enqueue(object: Callback<ResultResponse> {
            override fun onResponse(call: Call<ResultResponse>, response: Response<ResultResponse>) {
                if(response.body() != null) {
                    Log.d("getPostList()", " / " + response.body())
                    val result: ResultResponse = response.body()!!

                    when(result.code) {
                        1000 -> resultView.onGetResultSuccess(result.result)
                        else -> resultView.onGetResultFailure()
                    }
                }
            }

            override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                Log.d("getResult-fail", t.message.toString())
            }

        })
    }
}