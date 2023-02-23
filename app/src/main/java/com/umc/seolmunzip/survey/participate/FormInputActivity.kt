package com.umc.seolmunzip.survey.participate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.seolmunzip.post.detail.PostDetailActivity
import com.umc.seolmunzip.databinding.ActivityFormInputBinding
import com.umc.seolmunzip.my.survey.ParticipatedSurveyActivity
import com.umc.seolmunzip.post.list.FormListActivity
import com.umc.seolmunzip.survey.*

class FormInputActivity : AppCompatActivity(), FormDetailView, FormInputItem {

    private lateinit var binding: ActivityFormInputBinding
    private var postId: Long = 0L
    private var answerList: ArrayList<ArrayList<ArrayList<String>>> = arrayListOf()

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
        return FormInputRequest(postId, answerList)
    }

    override fun onFormDetailSuccess(formDetailResponse: FormDetailResponse) {
//        formResp = formDetailResponse
        binding.formInputRv.adapter = FormDetailRAdapter(formDetailResponse.result, this)
    }

    override fun onFormDetailFailure() {
    }

    override fun onFormSubmitSucess() {
        val intent = Intent(this, ParticipatedSurveyActivity::class.java)
        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        finish()
    }

    override fun onFormSubitFailure() {
        val intent = Intent(this, PostDetailActivity::class.java)
        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        finish()

        Toast.makeText(this, "답변 등록을 실패했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onInputItem(answer: ArrayList<ArrayList<String>>) {
        // 리스트에서 해당 아이템 아이디에 해당하는 요소를 검색해서
        // 있으면, answer로 바꾸고
        // 없으면, 추가한다
        for(i in 0 until answerList.size) {
            if(answer[0] == answerList[i][0]) {
                answerList[i] = answer
                Log.d("현재까지 추가된 있는 아이템 :", answerList.toString())
                return
            }
        }

        answerList.add(answer)
        Log.d("현재까지 추가된 새로운 아이템 :", answerList.toString())
    }
}