package com.example.umc_hackathon.temporary

import android.util.Log
import com.example.umc_hackathon.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SurveyListService() {
    private lateinit var surveyListView: SurveyListView

    fun setSurveyListView(surveyList: SurveyList) {
        this.surveyListView = surveyListView
    }

    fun getSurveyList() {
        val surveyListService = getRetrofit().create(SurveyListRetrofitInterface::class.java)

        surveyListService.getSurveyList().enqueue(object: Callback<SurveyListResponse> {
            override fun onResponse(call: Call<SurveyListResponse>, response: Response<SurveyListResponse>) {
                if(response.isSuccessful) {
                    val surveyListResponse: SurveyListResponse = response.body()!!

                    Log.d("SURVEYLIST_RESPONSE", surveyListResponse.toString())

                    when(val code = surveyListResponse.code) {
                        1000 -> {
                            surveyListView.onGetSurveyListSuccess(code, surveyListResponse.result!!)
                        }
                        else -> surveyListView.onGetSurveyListFailure(code, surveyListResponse.message)
                    }
                }
            }

            override fun onFailure(call: Call<SurveyListResponse>, t: Throwable) {
                surveyListView.onGetSurveyListFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })
    }
}