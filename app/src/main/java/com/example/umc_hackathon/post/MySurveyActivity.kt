package com.example.umc_hackathon.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.auth.MyPageActivity
import com.example.umc_hackathon.databinding.ActivityMySurveyBinding

class MySurveyActivity : AppCompatActivity(), MySurveyView {

    private lateinit var binding: ActivityMySurveyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 어댑터
        binding.mySurveyRv.layoutManager = GridLayoutManager(this, 2)
        binding.mySurveyRv.setHasFixedSize(true)
        getMySurvey()

        // 이벤트 리스너
        binding.mySurveyGoMyPageLl.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }

    private fun getMySurvey() {
        val postService = PostService()
        postService.setMySurveyView(this)
        postService.getMySurvey(getJwt().toString())

        Log.d("getMySurvey", " / InterestSurveyActivity에서 메소드")
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    override fun onGetMySurveyViewSuccess(mySurveyList: MySurveyResponse) {
        binding.mySurveyRv.adapter = MySurveyGridRAdapter(mySurveyList.result)
        Log.d("나의 설문조사 / ", "나의 설문조사 폼 목록을 불러오는데 성공했습니다")

    }

    override fun onGetMySurveyViewFailure() {
        Log.d("나의 설문조사 / ", "나의 설문조사 폼 목록을 불러오는데 실패했습니다")
    }
}