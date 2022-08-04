package com.example.umc_hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.auth.MyPageActivity
import com.example.umc_hackathon.databinding.ActivityParticipatedSurveyBinding

class ParticipatedSurveyActivity : AppCompatActivity() {
    val TAG: String = "<ParticipatedSurveyActivity>"
    var modelList = ArrayList<MySurvey>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityParticipatedSurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 리스트 생성
        for (i in 1..10){
            val mySurvey = MySurvey(title = "사회현상에 대한 소비자 인식 $i")
            this.modelList.add(mySurvey)
        }

        binding.ppsListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.ppsListRv.setHasFixedSize(true)
//        binding.ppsListRv.adapter = FormListRAdapter(modelList)

        binding.ppsGoMyPageLl.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}