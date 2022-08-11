package com.example.umc_hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.umc_hackathon.databinding.ActivityFormDetailBinding
import com.example.umc_hackathon.post.FormListActivity

class FormDetailActivity : AppCompatActivity() {

    val TAG: String = "<FormDetailActivity>"
    private lateinit var binding: ActivityFormDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("list_title")) {
            binding.formDetailTitleTv.text = intent.getStringExtra("list_title")
        }

        binding.formDetailGoFormListLl.setOnClickListener{
            val intent = Intent(this, FormListActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.formDetailParticipateBtn.setOnClickListener {
            val intent = Intent(this, FormInputActivity::class.java)
            startActivity(intent)
        }

        // 관심 버튼 하트 색깔

    }
}