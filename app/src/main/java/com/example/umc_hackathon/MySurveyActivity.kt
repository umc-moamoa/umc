package com.example.umc_hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.databinding.ActivityMyPageBinding
import com.example.umc_hackathon.databinding.ActivityMySurveyBinding

class MySurveyActivity : AppCompatActivity() {

    val TAG: String = "<MySurveyActivity>"
    var modelList = ArrayList<MySurvey>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for (i in 1..10){
            val mySurvey = MySurvey(title = "사회현상에 대한 소비자 인식 $i")
            this.modelList.add(mySurvey)
        }

        binding.mySurveyRv.layoutManager = GridLayoutManager(this, 2)
        binding.mySurveyRv.setHasFixedSize(true)
        binding.mySurveyRv.adapter = MySurveyGridRAdapter(modelList)

        binding.mySurveyGoMyPageLl.setOnClickListener{
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}