package com.example.umc_hackathon.survey

import android.util.Log
import com.example.umc_hackathon.getRetrofit
import com.example.umc_hackathon.my.survey.MyAnswerResponse
import com.example.umc_hackathon.my.survey.MyAnswerView
import com.example.umc_hackathon.post.modify.ModifyRequest
import com.example.umc_hackathon.post.modify.ModifyResponse
import com.example.umc_hackathon.post.modify.ModifyView
import com.example.umc_hackathon.survey.create.FormCreateRequest
import com.example.umc_hackathon.survey.create.FormCreateResponse
import com.example.umc_hackathon.survey.create.FormCreateView
import com.example.umc_hackathon.survey.participate.FormInputRequest
import com.example.umc_hackathon.survey.participate.FormInputResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormService {

    private lateinit var formCreateView: FormCreateView
    private lateinit var formDetailView: FormDetailView
    private lateinit var modifyView: ModifyView
    private lateinit var myAnswerView: MyAnswerView

    fun setFormCreateView(formCreateView: FormCreateView) {
        this.formCreateView = formCreateView
    }

    fun setFormDetailView(formDetailView: FormDetailView) {
        this.formDetailView = formDetailView
    }

    fun setModifyView(modifyView: ModifyView) {
        this.modifyView = modifyView
    }

    fun setMyAnswerView(myAnswerView: MyAnswerView) {
        this.myAnswerView = myAnswerView
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

    fun getFormDetail(postId: Long, accessToken: String, refreshToken: String) {
        val formService = getRetrofit().create(FormRetrofitInterface::class.java)

        formService.getFormDetail(postId, accessToken, refreshToken).enqueue(object: Callback<FormDetailResponse> {
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

    fun submitResult(formInputRequest: FormInputRequest, accessToken: String, refreshToken: String) {
        val formService = getRetrofit().create(FormRetrofitInterface::class.java)

        formService.submitAnswer(formInputRequest, accessToken, refreshToken).enqueue(object : Callback<FormInputResponse> {
            override fun onResponse(call: Call<FormInputResponse>, response: Response<FormInputResponse>) {
                if(response.isSuccessful) {
                    Log.d("submit-success", response.body().toString())
                    val inputResponse: FormInputResponse = response.body()!!

                    when(inputResponse.code) {
                        1000 -> formDetailView.onFormSubmitSucess()
                        else -> formDetailView.onFormSubitFailure()
                    }
                }
            }

            override fun onFailure(call: Call<FormInputResponse>, t: Throwable) {
                Log.d("submit-fail", t.message.toString())
            }

        })
    }

    fun getMyAnswer(postId: Long, accessToken: String, refreshToken: String) {
        val formService = getRetrofit().create(FormRetrofitInterface::class.java)

        formService.getMyAnswer(postId, accessToken, refreshToken).enqueue(object: Callback<MyAnswerResponse> {
            override fun onResponse(call: Call<MyAnswerResponse>, response: Response<MyAnswerResponse>) {
                if(response.isSuccessful) {
                    Log.d("getMyAnswer-success", response.body().toString())
                    val myAnswerResponse: MyAnswerResponse = response.body()!!

                    when(myAnswerResponse.code) {
                        1000 -> myAnswerView.onGetMyAnswerSuccess(myAnswerResponse)
                        else -> myAnswerView.onGetMyAnswerFailure()
                    }
                }
            }

            override fun onFailure(call: Call<MyAnswerResponse>, t: Throwable) {
                Log.d("getMyAnswer-fail", t.message.toString())
            }
        })
    }
}