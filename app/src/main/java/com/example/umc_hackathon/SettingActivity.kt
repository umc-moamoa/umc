package com.example.umc_hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_hackathon.auth.MyPageActivity
import com.example.umc_hackathon.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.settingCancelTv.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}