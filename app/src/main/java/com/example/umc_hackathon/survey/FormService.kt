package com.example.umc_hackathon.survey

import android.util.Log
import com.example.umc_hackathon.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormService {

    private lateinit var formCreateView: FormCreateView
    private lateinit var formDetailView: FormDetailView

    fun setFormCreateView(formCreateView: FormCreateView) {
        this.formCreateView = formCreateView
    }

    fun setFormDetailView(formDetailView: FormDetailView) {
        this.formDetailView = formDetailView
    }

    fun formCreate(formCreateRequest: FormCreateRequest, jwt: String) {
        val formService = getRetrofit().create(FormRetrofitInterface::class.java)

        formService.formCreate(formCreateRequest, jwt).enqueue(object: retrofit2.Callback<FormCreateResponse> {
            override fun onResponse(call: Call<FormCreateResponse>, response: Response<FormCreateResponse>) {
                if(response.body() != null) {
                    Log.d("formCreate()", response.body().toString())
                    val formCreateResponse: FormCreateResponse = response.body()!!

                    when(formCreateResponse.code) {
                        1000 -> formCreateView.onFormCreateSuccess()
                        else -> formCreateView.onFormCreateFailure()
                    }
                }
            }

            override fun onFailure(call: Call<FormCreateResponse>, t: Throwable) {
                Log.d("formCreate() 실패", t.message.toString())
            }
        })
    }

    fun getFormDetail(postId: Long, jwt: String) {
        val formService = getRetrofit().create(FormRetrofitInterface::class.java)

        formService.getFormDetail(postId, jwt).enqueue(object: Callback<FormDetailResponse> {
            override fun onResponse(call: Call<FormDetailResponse>, response: Response<FormDetailResponse>) {
                if(response.body() != null) {
                    Log.d("getFormDetail()", response.body().toString())
                    val formDetailResponse: FormDetailResponse = response.body()!!

                    when(formDetailResponse.code) {
                        1000 -> formDetailView.onFormDetailSuccess(formDetailResponse)
                        else -> formDetailView.onFormDetailFailure()
                    }
                }
            }

            override fun onFailure(call: Call<FormDetailResponse>, t: Throwable) {
                Log.d("getFormDetail() 실패", t.message.toString())
            }
        })
    }
}