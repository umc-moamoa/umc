package com.example.umc_hackathon.my.survey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.databinding.ActivityMyAnswerBinding
import com.example.umc_hackathon.survey.FormService

class MyAnswerActivity : AppCompatActivity(), MyAnswerView {

    private lateinit var binding: ActivityMyAnswerBinding
    private var postId: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postId = intent.getLongExtra("postId", postId)

        // Adapter
        binding.formMyAnswerRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.formMyAnswerRv.setHasFixedSize(true)
        getMyAnswer()

        binding.formMyAnswerCloseBtn.setOnClickListener {
            val intent = Intent(this, ParticipatedSurveyActivity::class.java)
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

    private fun getMyAnswer() {
        val formService = FormService()
        formService.setMyAnswerView(this)
        formService.getMyAnswer(postId, getAccessToken().toString(), getRefreshToken().toString())
    }

    override fun onGetMyAnswerSuccess(myAnswerResponse: MyAnswerResponse) {
        binding.formMyAnswerRv.adapter = MyAnswerRAdapter(myAnswerResponse.result)
    }

    override fun onGetMyAnswerFailure() {
        Toast.makeText(this, "나의 답변 보기/ 답변 불러오기 실패;;", Toast.LENGTH_SHORT).show()
    }
}