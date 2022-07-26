package com.example.umc_hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG: String = "<MainActivity>"
    var modelList = ArrayList<MySurvey>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivProfileMain.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.popularSurveyListLayoutHeader.setOnClickListener {
            val intent = Intent(this, FormListActivity::class.java)
            startActivity(intent)
        }

        binding.waitingSurveyListHeader.setOnClickListener {
            val intent = Intent(this, FormListActivity::class.java)
            startActivity(intent)
        }

        // 리스트 생성
        for (i in 1..10){
            val mySurvey = MySurvey(title = "사회현상에 대한 소비자 인식 $i")
            this.modelList.add(mySurvey)
        }

        binding.rvWaitingSurveyList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvWaitingSurveyList.setHasFixedSize(true)
        binding.rvWaitingSurveyList.adapter = WaitingSurveyListRAdapter(modelList)
    }
}