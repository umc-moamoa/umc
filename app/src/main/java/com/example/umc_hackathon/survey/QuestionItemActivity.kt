package com.example.umc_hackathon.survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.ActivityQuestionItemBinding

class QuestionItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionItemBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_question_item)

        // 스피너


    }
}