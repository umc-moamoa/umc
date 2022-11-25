package com.example.umc_hackathon.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.umc_hackathon.*
import com.example.umc_hackathon.databinding.ActivityMyPageBinding
import com.example.umc_hackathon.post.*

class MyPageActivity : AppCompatActivity(), UserInfoView {

    private final var TAG = "MyPageActivity"
    private lateinit var binding: ActivityMyPageBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // userInfo api 호출
        userInfo()

        binding.myPageGoMainLl.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

        binding.myPageSettingIv.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

        binding.myPageMySurveyLl.setOnClickListener {
            val intent = Intent(this, MySurveyActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.myPageInterestSurveyLl.setOnClickListener {
            val intent = Intent(this, InterestSurveyActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.myPageJoinSurveyLl.setOnClickListener {
            val intent = Intent(this, ParticipatedSurveyActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.myPagePointLl.setOnClickListener {
            val intent = Intent(this, MyPointActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
    }

    override fun onStop() {
        super.onStop()
        userInfo()
    }

    private fun userInfo() {
        Log.d(TAG, "userInfo() 실행")

        val authService = AuthService()
        authService.setUserInfoView(this)
        authService.userInfo(getAccessToken().toString(), getRefreshToken().toString())
    }

    private fun getAccessToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("accessToken", "")
    }

    private fun getRefreshToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("refreshToken", "")
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

        val authSpf = getSharedPreferences("auth", MODE_PRIVATE)
        val authEditor = authSpf.edit()
        authEditor.remove("accessToken")
        authEditor.remove("refreshToken")
        authEditor.commit()

        val nickNameSpf = getSharedPreferences("nickName", MODE_PRIVATE)
        val nickNameEditor = nickNameSpf.edit()
        nickNameEditor.remove("nickName")
        nickNameEditor.commit()

        val intent = Intent(this, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}