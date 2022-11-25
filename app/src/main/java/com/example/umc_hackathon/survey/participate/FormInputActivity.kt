package com.example.umc_hackathon.survey.participate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.post.detail.PostDetailActivity
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.ActivityFormInputBinding
import com.example.umc_hackathon.post.list.FormListActivity
import com.example.umc_hackathon.survey.*

class FormInputActivity : AppCompatActivity(), FormDetailView {

    private lateinit var binding: ActivityFormInputBinding
    private var postId: Long = 0L
    private lateinit var formResp: FormDetailResponse
    private lateinit var answer: Answer
    private lateinit var formInputRequest: FormInputRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 설문조사 상세 페이지에서 설문 조사 참여 페이지로 넘어올 때 넘어오는 것
        postId = intent.getLongExtra("postId", postId)

        // 어댑터
        binding.formInputRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.formInputRv.setHasFixedSize(true)
        getFormDetail()

        // 이벤트 리스너
        binding.formInputCancelBtn.setOnClickListener {
            val intent = Intent(this, FormListActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

        binding.formInputSubmitBtn.setOnClickListener {
            submitAnswer()
            val intent = Intent(this, PostDetailActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }

//    private fun saveAccessToken(accessToken: String) {
//        val spf = getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
//        val editor = spf.edit()
//
//        editor.putString("accessToken", accessToken)
//        editor.apply()
//
//        Log.d("엑세스토근", "세이브")
//    }
//
//    private fun getReAccessToken() {
//        val authService = AuthService()
//        authService.setReAccessTokenView(this)
//        authService.getReAccessToken(getAccessToken().toString(), getRefreshToken().toString())
//    }

    private fun getAccessToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("accessToken", "")
    }

    private fun getRefreshToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("refreshToken", "")
    }

    private fun getFormDetail() {
        val formService = FormService()
        formService.setFormDetailView(this)
        formService.getFormDetail(postId, getAccessToken().toString(), getRefreshToken().toString())
    }

    private fun submitAnswer() {
        val formService = FormService()
        formService.setFormDetailView(this)
        formService.submitResult(getAnswer(), getAccessToken().toString(), getRefreshToken().toString())
    }

    //적은 답 가져오기
    private fun getAnswer(): FormInputRequest {
        val questionList: List<FormDetail> = formResp.result

        for(i in questionList.indices) {
            Log.d("getAnswer()", "포맷은 " + questionList[i].format)
            if(questionList[i].format == 1) {
                val answer = findViewById<RadioGroup>(R.id.question_input_item_rg).checkedRadioButtonId
                Log.d("포맷이 1일때", " " + answer + "체크됨")
            }
        }


        return formInputRequest
    }

    override fun onFormDetailSuccess(formDetailResponse: FormDetailResponse) {
        formResp = formDetailResponse
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