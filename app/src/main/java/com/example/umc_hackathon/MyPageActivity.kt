package com.example.umc_hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_hackathon.databinding.ActivityMyPageBinding

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

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