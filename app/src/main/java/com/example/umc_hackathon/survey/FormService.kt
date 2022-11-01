package com.example.umc_hackathon.survey

import android.util.Log
import android.widget.Toast
import com.example.umc_hackathon.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormService {

    private lateinit var formCreateView: FormCreateView
    private lateinit var formDetailView: FormDetailView
    private lateinit var modifyView: ModifyView

    fun setFormCreateView(formCreateView: FormCreateView) {
        this.formCreateView = formCreateView
    }

    fun setFormDetailView(formDetailView: FormDetailView) {
        this.formDetailView = formDetailView
    }

    fun setModifyView(modifyView: ModifyView) {
        this.modifyView = modifyView
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
                        else -> formCreateView.onFormCreateFailure(formCreateResponse.code)
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

    fun formModify(modifyRequest: ModifyRequest, jwt: String) {
        val formService = getRetrofit().create(FormRetrofitInterface::class.java)

        formService.formModify(modifyRequest, jwt).enqueue(object: retrofit2.Callback<ModifyResponse> {
            override fun onResponse(call: Call<ModifyResponse>, response: Response<ModifyResponse>) {
                if(response.body() != null) {
                    Log.d("formModify()", response.body().toString())
                    val modifyResponse: ModifyResponse = response.body()!!

                    when(modifyResponse.code) {
                        1000 -> modifyView.onFormModifySuccess(modifyResponse)
                        else -> modifyView.onFormModifyFailure()
                    }
                }
            }

            override fun onFailure(call: Call<ModifyResponse>, t: Throwable) {
                Log.d("formModify() 실패", t.message.toString())
            }
        })
    }
}