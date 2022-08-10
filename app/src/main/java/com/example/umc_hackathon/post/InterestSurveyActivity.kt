package com.example.umc_hackathon.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.auth.MyPageActivity
import com.example.umc_hackathon.databinding.ActivityInterestSurveyBinding

class InterestSurveyActivity : AppCompatActivity(), InterestSurveyListView {

    private lateinit var binding: ActivityInterestSurveyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInterestSurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 어댑터
        binding.interestSurveyListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.interestSurveyListRv.setHasFixedSize(true)
        getInterestSurveyList()

        // 이벤트 리스너
        binding.interestSurveyGoMyPageLl.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getInterestSurveyList() {
        val postService = PostService()
        postService.setInterestSurveyListView(this)
        postService.getInterestSurveyList()
        
        Log.d("getInterestSurveyList", " / InterestSurveyActivity에서 메소드")
    }

    override fun onGetInterestSurveyListSuccess(postList: PostListResponse) {
        binding.interestSurveyListRv.adapter = FormListRAdapter(postList.result)
        Log.d("관심있는 설문조사 / ", "관심있는 설문조사 폼 목록을 불러오는데 성공했습니다")
    }

    override fun onGetInterestSurveyListFailure() {
        Log.d("관심있는 설문조사 / ", "관심있는 설문조사 폼 목록을 불러오는데 실패했습니다")
    }


}