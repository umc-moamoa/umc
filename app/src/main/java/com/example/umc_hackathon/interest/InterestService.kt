package com.example.umc_hackathon.interest

import android.util.Log
import com.example.umc_hackathon.getRetrofit
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.security.auth.callback.Callback

class InterestService {
    private lateinit var interestView: InterestView

    fun setInterestView(interestView: InterestView) {
        this.interestView = interestView
    }

    fun makeInterest(postId: Int, userId: Int) {
        val makeInterestService = getRetrofit().create(InterestRetrofitInterface::class.java)

        makeInterestService.makeInterest(postId, userId).enqueue(object : retrofit2.Callback<InterestResponse>{
            override fun onResponse(call: Call<InterestResponse>, response: Response<InterestResponse>
            ) {
                if (response.isSuccessful) { //코드 실패 짜기
                    val interestResponse = response.body()!!
                    interestView.onMakeInterest(interestResponse)
                    Log.d("make-interest-success", interestResponse.result.toString())
                }
            }

            override fun onFailure(call: Call<InterestResponse>, t: Throwable) {
                Log.d("make-interest-fail", t.toString())
            }

        })
    }
}