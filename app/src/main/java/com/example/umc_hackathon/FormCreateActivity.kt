package com.example.umc_hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_hackathon.databinding.ActivityFormCreateBinding
import com.example.umc_hackathon.databinding.ActivityFormDetailBinding

class FormCreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFormCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}