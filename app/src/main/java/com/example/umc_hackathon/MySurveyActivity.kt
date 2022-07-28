package com.example.umc_hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_hackathon.databinding.ActivityMyPageBinding
import com.example.umc_hackathon.databinding.ActivityMySurveyBinding

class MySurveyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}