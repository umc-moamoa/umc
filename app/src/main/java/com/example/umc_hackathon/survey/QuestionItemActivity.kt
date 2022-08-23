package com.example.umc_hackathon.survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.ActivityQuestionItemBinding

class QuestionItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}