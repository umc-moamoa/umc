package com.umc.seolmunzip.survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.seolmunzip.databinding.ActivityQuestionItemBinding

class QuestionItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}