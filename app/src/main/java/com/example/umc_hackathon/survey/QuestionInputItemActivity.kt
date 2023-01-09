package com.example.umc_hackathon.survey

import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_hackathon.databinding.ActivityQuestionInputItemBinding

class QuestionInputItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionInputItemBinding

//    val optionGroup: RadioGroup = binding.questionInputItemRg
//    val checkBoxLayout: LinearLayout = binding.questionInputCheckboxLl
//    val shortAnswerEt: EditText = binding.questionInputShortAnswerEt
//    val longAnswerEt: EditText = binding.questionInputLongAnswerEt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionInputItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}