package com.example.umc_hackathon.my.survey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.auth.*
import com.example.umc_hackathon.auth.dto.ReAccessTokenResponse
import com.example.umc_hackathon.auth.view.ReAccessTokenView
import com.example.umc_hackathon.databinding.ActivityInterestSurveyBinding
import com.example.umc_hackathon.my.MyPageActivity
import com.example.umc_hackathon.post.list.FormListRAdapter
import com.example.umc_hackathon.post.PostService
import com.example.umc_hackathon.post.list.PostListResponse

class InterestSurveyActivity : AppCompatActivity(), InterestSurveyListView, ReAccessTokenView {

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
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }

    private fun getInterestSurveyList() {
        val postService = PostService()
        postService.setInterestSurveyListView(this)
        postService.getInterestSurveyList(getAccessToken().toString(), getRefreshToken().toString())
        
        Log.d("getInterestSurveyList", " / InterestSurveyActivity에서 메소드")
    }

    private fun getAccessToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("accessToken", "")
    }

    private fun getRefreshToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("refreshToken", "")
    }

    private fun getReAccessToken() {
        val authService = AuthService()
        authService.setReAccessTokenView(this)
        authService.getReAccessToken(getAccessToken().toString(), getRefreshToken().toString())
    }

    private fun saveAccessToken(accessToken: String) {
        val spf = getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("accessToken", accessToken)
        editor.apply()

        Log.d("엑세스토근", "세이브")
    }

    override fun onGetInterestSurveyListSuccess(postList: PostListResponse) {
        binding.interestSurveyListRv.adapter = FormListRAdapter(postList.result)
        Log.d("관심있는 설문조사 / ", "관심있는 설문조사 폼 목록을 불러오는데 성공했습니다")
    }

    override fun onGetInterestSurveyListFailure(result: PostListResponse) {
        Log.d("관심있는 설문조사 / ", "관심있는 설문조사 폼 목록을 불러오는데 실패했습니다")

        if(result.code == 2002) {
            getReAccessToken()
        } else {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onGetReAccessTokenSuccess(res: ReAccessTokenResponse) {
        Log.d("액세스토근", "을 재발급했습니다.")
        saveAccessToken(res.result)
    }

    override fun onGetReAccessTokenFailure() {
        Log.d("onGetReAccessTokenFailure()", " 실패 / ")
    }
}