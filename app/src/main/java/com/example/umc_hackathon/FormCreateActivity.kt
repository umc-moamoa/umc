package com.example.umc_hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.databinding.ActivityFormCreateBinding
import com.example.umc_hackathon.databinding.ActivityFormDetailBinding

class FormCreateActivity : AppCompatActivity() {

    val TAG: String = "<FormCreateActivity>"
    var modelList = ArrayList<MyQuestion>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFormCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 리스트 생성
        for (i in 1..10){
            val myQuestion = MyQuestion(title = "$i.질문을 적겠습니까? ")
            this.modelList.add(myQuestion)
        }

        binding.rvFormCreate.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvFormCreate.setHasFixedSize(true)
        binding.rvFormCreate.adapter = MyRecyclerAdapter2(modelList)
    }
}