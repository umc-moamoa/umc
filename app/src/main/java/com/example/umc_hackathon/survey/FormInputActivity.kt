package com.example.umc_hackathon.survey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_hackathon.FormDetailActivity
import com.example.umc_hackathon.databinding.ActivityFormInputBinding

class FormInputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFormInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.formInputCancelBtn.setOnClickListener {
            val intent = Intent(this, FormDetailActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.formInputSubmitBtn.setOnClickListener {
            val intent = Intent(this, FormDetailActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}