package com.example.umc_hackathon.survey

import android.util.Log
import com.example.umc_hackathon.getRetrofit
import retrofit2.Call
import retrofit2.Response

class FormService {

    private lateinit var formCreateView: FormCreateView

    fun setFormCreateView(formCreateView: FormCreateView) {
        this.formCreateView = formCreateView
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
}