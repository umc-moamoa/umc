package com.example.umc_hackathon.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.umc_hackathon.*
import com.example.umc_hackathon.databinding.ActivityMyPageBinding
import com.example.umc_hackathon.post.InterestSurveyActivity
import com.example.umc_hackathon.post.MainActivity
import com.example.umc_hackathon.post.MySurveyActivity
import com.example.umc_hackathon.post.ParticipatedSurveyActivity

class MyPageActivity : AppCompatActivity(), UserInfoView {

    private lateinit var binding: ActivityMyPageBinding
    var userId: Long = 1
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // UserInfo api 호출
        userInfo()

        binding.myPageGoMainLl.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.myPageSettingIv.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.myPageMySurveyLl.setOnClickListener {
            val intent = Intent(this, MySurveyActivity::class.java)
            startActivity(intent)
        }

        binding.myPageInterestSurveyLl.setOnClickListener {
            val intent = Intent(this, InterestSurveyActivity::class.java)
            startActivity(intent)
        }

        binding.myPageJoinSurveyLl.setOnClickListener {
            val intent = Intent(this, ParticipatedSurveyActivity::class.java)
            startActivity(intent)
        }

        binding.myPagePointLl.setOnClickListener {
            val intent = Intent(this, MyPointActivity::class.java)
            startActivity(intent)
        }
    }

    private fun userInfo() {
        Log.d("activity/userInfo()", "메소드")

        val authService = AuthService()
        authService.setUserInfoView(this)
        authService.userInfo(getJwt().toString())
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    private fun saveNickName(nickName: String) {
        Log.d("saveNickName", " : " + nickName)
        val spf = getSharedPreferences("nickName", MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("nickName", nickName)
        editor.apply()
    }

    override fun onUserInfoSuccess(code: Int, result: UserInfoResult) {
        Log.d("onUserInfoSuccess/성공", "회원 정보 불러오기에 성공했습니다")

        binding.myPageMemberNameTv.text = result.nickName
        binding.myPageMemberPointTv.text = result.point.toString() + "P 포인트"
        binding.myPageMemberSurveyTv.text = "진행 중인 설문조사 " + result.postCount.toString() + "개"

        saveNickName(result.nickName)
    }

    override fun onUserInfoFailure(code: Int) {
        Toast.makeText(this, "회원정보 불러오기에 실패했습니다", Toast.LENGTH_SHORT).show()
    }
}