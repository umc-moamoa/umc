package com.example.umc_hackathon.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.umc_hackathon.*
import com.example.umc_hackathon.databinding.ActivityMyPageBinding
import com.example.umc_hackathon.temporary.MainActivity

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getLongExtra("userId", 1)
//        Toast.makeText(this, "마이페이지에서 부름: " + userId.toString(), Toast.LENGTH_SHORT).show()

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
}