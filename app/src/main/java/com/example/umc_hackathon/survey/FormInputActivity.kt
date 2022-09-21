package com.example.umc_hackathon.survey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.FormDetailActivity
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.ActivityFormInputBinding

class FormInputActivity : AppCompatActivity(), FormDetailView {

    private lateinit var binding: ActivityFormInputBinding
    private var postId: Long = 0L
    private lateinit var formDetailResponse: FormDetailResponse
    private lateinit var answer: Answer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 설문조사 상세 페이지에서 살문 조사 참여 페이지로 넘어올 때 넘어오는 것
        postId = intent.getLongExtra("postId", postId)

        // 어댑터
        binding.formInputRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.formInputRv.setHasFixedSize(true)
        getFormDetail()

        // 이벤트 리스너
        binding.formInputCancelBtn.setOnClickListener {
            val intent = Intent(this, FormDetailActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

        binding.formInputSubmitBtn.setOnClickListener {
            submitAnswer()
            val intent = Intent(this, FormDetailActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    private fun getFormDetail() {
        val formService = FormService()
        formService.setFormDetailView(this)
        formService.getFormDetail(postId, getJwt()!!)
    }

    private fun submitAnswer() {
        val formService = FormService()
        formService.setFormDetailView(this)
        formService.submitResult(getAnswer(), getJwt()!!)
    }

    //적은 답 가져오기
    private fun getAnswer(): FormInputRequest {
        lateinit var formInputRequest: FormInputRequest
        var answerList: MutableList<Answer> = arrayListOf()

        val questionList: List<FormDetail> = formDetailResponse.result

        for (i in questionList.indices) {
            when (questionList[i].format) {
                1 -> {
                    val radioGroup: RadioGroup = findViewById(R.id.question_input_item_rg)
                    answer.answer = radioGroup.checkedRadioButtonId.toString()
                }
                2 -> {
        //                val checkBox: CheckBox = findViewById(CheckBox)
                }
                3 -> {
                    val short: EditText = findViewById(R.id.question_input_short_answer_et)
                    answer.answer = short.text.toString()
                }
                else -> {
                    val long: EditText = findViewById(R.id.question_input_long_answer_et)
                    answer.answer = long.text.toString()
                }
            }
            answer.detailId = i.toLong()
            answerList.add(answer)
        }
        formInputRequest.postId = postId
        formInputRequest.postDetailResults = answerList
        return formInputRequest
    }

    override fun onFormDetailSuccess(formDetailResponse: FormDetailResponse) {
        binding.formInputRv.adapter = FormDetailRAdapter(formDetailResponse.result)
        Toast.makeText(this, "설문 조사 문항 불러오기에 성공했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onFormDetailFailure() {
        Toast.makeText(this, "설문 조사 문항 불러오기에 실패했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onFormSubmitSucess() {
        TODO("Not yet implemented")
    }

    override fun onFormSubitFailure() {
        TODO("Not yet implemented")
    }
}