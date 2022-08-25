package com.example.umc_hackathon.survey

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import com.example.umc_hackathon.databinding.ActivityQuestionInputItemBinding

class QuestionInputItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionInputItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionInputItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}