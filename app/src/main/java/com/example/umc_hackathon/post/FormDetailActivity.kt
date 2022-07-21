package com.example.umc_hackathon.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_hackathon.databinding.ActivityFormDetailBinding
import com.example.umc_hackathon.databinding.ActivityMainBinding

class FormDetailActivity : AppCompatActivity() {

    val TAG: String = "<FormDetailActivity>"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFormDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("title")) {
            binding.tvSurveyTitle.text = intent.getStringExtra("title")
        }
    }
}