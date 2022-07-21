package com.example.umc_hackathon

import android.content.Intent
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

        if(intent.hasExtra("list_title")) {
            binding.tvSurveyTitle.text = intent.getStringExtra("list_title")
        }

        binding.goBackToMain.setOnClickListener{
            val intent = Intent(this, FormListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}