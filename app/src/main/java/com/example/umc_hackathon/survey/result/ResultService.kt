package com.example.umc_hackathon.survey.result

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

    fun getDetailId(postId: Long) {
        val resultService = getRetrofit().create(PostRetrofitInterface::class.java)

        resultService.getDetailId(postId).enqueue(object : Callback<DetailIdResponse> {
            override fun onResponse(
                call: Call<DetailIdResponse>,
                response: Response<DetailIdResponse>
            ) {
                if (response.body() != null) {
                    Log.d("getResult()", " / " + response.body())
                    val result: DetailIdResponse = response.body()!!

                    when (result.code) {
                        1000 -> resultView.onGetDetailIdSuccess(result)
                        else -> resultView.onGetDetailIdFailure(result.message)
                    }
                }
            }

            override fun onFailure(call: Call<DetailIdResponse>, t: Throwable) {
                Log.d("getDetailId-fail", t.message.toString())
            }

        })
    }

    fun getResult(postDetailId: Long) {
        val resultService = getRetrofit().create(PostRetrofitInterface::class.java)

        resultService.getResults(postDetailId).enqueue(object : Callback<ResultResponse> {
            override fun onResponse(
                call: Call<ResultResponse>,
                response: Response<ResultResponse>
            ) {
                if (response.body() != null) {
                    Log.d("getResult()", " / " + response.body())
                    val result: ResultResponse = response.body()!!

                    when (result.code) {
                        1000 -> resultView.onGetResultSuccess(result.result)
                        else -> resultView.onGetResultFailure(result.message)
                    }
                }
            }

            override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                Log.d("getResult-fail", t.message.toString())
            }
        })
    }
}