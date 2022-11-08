package com.example.umc_hackathon.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.auth.AuthService
import com.example.umc_hackathon.auth.MyPageActivity
import com.example.umc_hackathon.auth.ReAccessTokenResponse
import com.example.umc_hackathon.auth.ReAccessTokenView
import com.example.umc_hackathon.databinding.ActivityMySurveyBinding

class MySurveyActivity : AppCompatActivity(), MySurveyView, ReAccessTokenView {

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
        postService.getMySurvey(getAccessToken().toString(), getRefreshToken().toString())

        Log.d("getMySurvey", " / InterestSurveyActivity에서 메소드")
    }

    private fun saveAccessToken(accessToken: String) {
        val spf = getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("accessToken", accessToken)
        editor.apply()

        Log.d("엑세스토근", "세이브")
    }

    private fun getReAccessToken() {
        val authService = AuthService()
        authService.setReAccessTokenView(this)
        authService.getReAccessToken(getAccessToken().toString(), getRefreshToken().toString())
    }

    private fun getAccessToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("accessToken", "")
    }

    private fun getRefreshToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("refreshToken", "")
    }

    override fun onGetMySurveyViewSuccess(mySurveyList: MySurveyResponse) {
        binding.mySurveyRv.adapter = MySurveyGridRAdapter(mySurveyList.result)
        Log.d("나의 설문조사 / ", "나의 설문조사 폼 목록을 불러오는데 성공했습니다")

    }

    override fun onGetMySurveyViewFailure(mySurveyList: MySurveyResponse) {
        Log.d("나의 설문조사" , "실패했습니다 " + mySurveyList.code)

        if(mySurveyList.code == 2002) {
            getReAccessToken()
        }
    }

    override fun onGetReAccessTokenSuccess(res: ReAccessTokenResponse) {
        Log.d("액세서 코톤", "재발급성공했습다")
    }

    override fun onGetReAccessTokenFailure() {
        Log.d("액세스 코튼", "재발급 실패했습니다")
    }
}